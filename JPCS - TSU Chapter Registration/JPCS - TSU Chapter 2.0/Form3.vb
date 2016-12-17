Imports System.Data.SqlClient

Public Class Form3
    Dim cmd As New SqlCommand


    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Form1.Show()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Form4.Show()
    End Sub

    Private Sub Form3_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.Anchor = AnchorStyles.Bottom Or _
      AnchorStyles.Left Or AnchorStyles.Right Or _
      AnchorStyles.Top
    End Sub

    Private Sub Panel2_Paint(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PaintEventArgs) Handles Panel2.Paint

    End Sub

    Private Sub Panel3_Paint(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PaintEventArgs) Handles Panel3.Paint

    End Sub

    Private Sub Panel1_Paint(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PaintEventArgs) Handles Panel1.Paint
        cmd.CommandText = "select * from StudentsFeeFirst WHERE  Date ='?12321'"

        Dim param As SqlParameterCollection = cmd.Parameters
        MsgBox(param.Count)

    End Sub
End Class