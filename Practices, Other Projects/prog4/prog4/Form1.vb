Public Class Form1

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub TextBox1_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub Label1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub TextBox2_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub Label2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        If TextBox1.Text = "barangayoff" And TextBox2.Text = "adminpass" Then
            MsgBox("Welcome Admin", MsgBoxStyle.Information, "Login ")
            Form2.Show()
            Me.Hide()
        ElseIf TextBox1.Text = "barangayoff" And TextBox2.Text = "member" Then
            MsgBox("Welcome. You are now logged in", MsgBoxStyle.Information, "Login ")
            Form3.Show()
            Me.Hide()
        Else
            If TextBox1.Text = "" And TextBox2.Text = "" Then
                MsgBox("Input Username & password", MsgBoxStyle.Exclamation, "ERROR")
            Else
                If TextBox1.Text = " " Then
                    MsgBox("Enter Username", MsgBoxStyle.Critical, "ERROR")
                Else
                    If TextBox2.Text = "" Then
                        MsgBox("Enter Password", MsgBoxStyle.Critical, "ERROR ")
                    Else
                        MsgBox("Invalid User ID & Password!", MsgBoxStyle.Critical, "TRY AGAIN")
                    End If
                End If
            End If
        End If
    End Sub
End Class
