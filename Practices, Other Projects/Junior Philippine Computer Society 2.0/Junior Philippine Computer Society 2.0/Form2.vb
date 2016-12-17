
Public Class Form2
    
    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        If TextBox1.Text.Trim = "juniorPCS" Then
            Form3.Show()
            Me.Hide()
            TextBox1.Clear()
        Else
            MsgBox(" UNAUTHORIZED USE OF THIS SYSTEM IS PUNISHABLE BY LAW", MsgBoxStyle.Exclamation)
        End If
    End Sub
End Class