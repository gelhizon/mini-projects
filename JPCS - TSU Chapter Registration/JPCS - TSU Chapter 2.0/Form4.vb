Public Class Form4

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        If TextBox1.Text = "juniorPCS" Then
            Form2.Show()
            Me.Hide()
        Else
            MsgBox("PLEASE TYPE THE CORRECT PASSWORD")
        End If
        TextBox1.Clear()
    End Sub
End Class