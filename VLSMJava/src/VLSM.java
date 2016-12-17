import java.util.Arrays;

public class VLSM {
	private String ip_address;
	private int mask;
	private int[] octets;
	private int[] hosts;

	public void setIPAddress(String ip_address) {
		this.ip_address = ip_address;
		setOctets(ip_address);
	}

	private void setOctets(String ip_address) {
		String octets[] = ip_address.split("\\.");
		String lastoctet[] = octets[3].split("/");
		this.mask = Integer.parseInt(lastoctet[1]);
		this.octets[0] = Integer.parseInt(octets[0]);
		this.octets[1] = Integer.parseInt(octets[1]);
		this.octets[2] = Integer.parseInt(octets[2]);
		this.octets[3] = Integer.parseInt(lastoctet[0]);
	}

	public void setHosts(int hosts[]) {
		Arrays.sort(hosts);
		this.hosts = new int[hosts.length];
		for (int i = 0; i < hosts.length; i++) {
			this.hosts[i] = hosts[i];
		}
	}

	public String getIPAddress() {
		return this.ip_address;
	}

	public long[] getRB() {
		long[] rb = new long[hosts.length];
		for (int i = hosts.length - 1; i >= 0; i--) {
			double log = Math.log(hosts[i] + 2) / Math.log(2);
			if (log == (long) log) {
				rb[i] = (long) log;
			} else {
				rb[i] = (long) log + 1;
			}
		}
		return rb;
	}

	public long[] getBB() {
		long[] bb = new long[hosts.length];
		for (int i = hosts.length - 1; i >= 0; i--) {
			bb[i] = 32 - getRB()[i];
		}
		return bb;
	}

	public String[] getNetworks() {
		if (isValid()) {
			String[] networks = new String[hosts.length];
			for (int i = hosts.length - 1; i >= 0; i--) {
				int allocatedsize = (int) Math.pow(2, getRB()[i]);

				octets[3] = ((octets[3] + allocatedsize) % 256);
				octets[2] += (int) ((octets[3] + allocatedsize) / 256);
				octets[2] = ((octets[2]) % 256);
				octets[1] += (int) (octets[2] / 256);
				octets[1] = ((octets[1]) % 256);
				octets[0] += (int) (octets[1] / 256);

				networks[Math.abs(i - (hosts.length - 1))] = Integer
						.toString(octets[0])
						+ "."
						+ Integer.toString(octets[1])
						+ "."
						+ Integer.toString(octets[2])
						+ "."
						+ Integer.toString(octets[3]) + "/" + (32 - getRB()[i]);
			}
			return networks;
		} else
			return null;
	}

	public long getHostsAvailable() {
		return (long) Math.pow(2, 32 - mask);
	}

	public boolean isValid() {
		long hostsneeded = 0;
		for (int i = hosts.length - 1; i >= 0; i--) {
			hostsneeded += Math.pow(2, getRB()[i]);
		}
		if (hostsneeded > getHostsAvailable())
			return false;
		else
			return true;
	}

}
