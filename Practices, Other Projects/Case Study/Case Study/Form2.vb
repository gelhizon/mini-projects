Imports System.Data.OleDb
Public Class Form2
    Public connstring As String = " Provider=Microsoft.ACE.OLEDB.12.0;Data Source=C:\Users\H8\Desktop\Case Study\Case Study\Database1.accdb;persist security info=false"
    Public con As New OleDbConnection

    Private Sub Form2_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'TODO: This line of code loads data into the 'Database1DataSet.Add' table. You can move, or remove it, as needed.
        con.ConnectionString = connstring
        con.Open()
        Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)
    End Sub




  
    Private Sub Panel4_Paint(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PaintEventArgs)

    End Sub

    Private Sub FillToolStripButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Try
            Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)
        Catch ex As System.Exception
            System.Windows.Forms.MessageBox.Show(ex.Message)
        End Try

    End Sub

    Private Sub ToolStripButton1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ToolStripButton1.Click
        Form3.Show()

    End Sub

    Private Sub ToolStripButton2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ToolStripButton2.Click
        Form5.Show()

    End Sub

    Private Sub ToolStripLabel1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ToolStripLabel1.Click
        AddDataGridView.Rows.Remove(AddDataGridView.SelectedRows(0))
        Me.TableAdapterManager.UpdateAll(Database1DataSet)
    End Sub

    Private Sub ToolStripButton4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ToolStripButton4.Click
        Form6.ID = AddDataGridView.SelectedCells(0).Value
        Form6.Show()

    End Sub
End Class