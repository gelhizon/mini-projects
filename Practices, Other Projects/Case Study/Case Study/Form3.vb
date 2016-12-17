Imports System.Data.OleDb
Public Class Form3
    Public connstring As String = " Provider=Microsoft.ACE.OLEDB.12.0;Data Source=C:\Users\H8\Desktop\Case Study\Case Study\Database1.accdb;persist security info=false"
    Public con As New OleDbConnection


   

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        con.ConnectionString = connstring
        con.Open()

        Try
            Dim SqlQuery As String = "INSERT INTO [Add] ([First Name], [Last Name],[Birth Date], [Age], [Status], [Gender], [Nationality], [Position], [Date Hired], [Job Status], [Salary/Hour], [Working Hours/Pay Period], [Street], [Town], [Municipality], [Contact No], [Email]) VALUES ('" & TextBox2.Text & "', '" & TextBox3.Text & "', '" & DateTimePicker1.Text & "', '" & TextBox6.Text & "', '" & ComboBox1.Text & "', '" & ComboBox2.Text & "', '" & TextBox8.Text & "', '" & TextBox21.Text & "', '" & DateTimePicker2.Text & "', '" & ComboBox3.Text & "', '" & TextBox17.Text & "', '" & TextBox18.Text & "', '" & TextBox9.Text & "', '" & TextBox10.Text & "', '" & TextBox11.Text & "', '" & TextBox12.Text & "', '" & TextBox13.Text & "')"

            Dim SqlCommand As New OleDbCommand

            With SqlCommand
                .CommandText = SqlQuery
                .Connection = con
                .ExecuteNonQuery()
            End With

            MsgBox("One record Successfully Added")

        Catch ex As Exception

            MsgBox(ex.ToString)

        End Try


    End Sub

    Private Sub Form3_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        con.ConnectionString = connstring

        If con.State = ConnectionState.Closed Then
            MsgBox("OPEN")
        Else
            MsgBox("CLOSED")
        End If



    End Sub

    Private Sub Panel1_Paint(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PaintEventArgs)

    End Sub

    Private Sub GroupBox1_Enter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles GroupBox1.Enter

    End Sub

    Private Sub Label7_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Label7.Click

    End Sub
End Class