Public Class Form1
    Dim Msg As Integer
    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click

        Msg = MsgBox("ADMIN ?", vbYesNo + vbExclamation, "JUNIOR PHILIPPINE COMPUTER SOCIETY")
        If Msg = 6 Then
            Form2.Show()
        Else
            Form3.DeleteToolStripMenuItem1.Enabled = False
            Form3.Show()
        End If
       

    End Sub

    
End Class
