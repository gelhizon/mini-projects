Public Class Form6
    Public ID As Integer

    Private Sub Form6_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Dim row As DataRow = AddTableAdapter.GetDataByID(ID).Rows(0)

        TextBox1.Text = row("Employee ID")
    End Sub

    Private Sub AddBindingSource_CurrentChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AddBindingSource.CurrentChanged

    End Sub
End Class