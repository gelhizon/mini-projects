Public Class Form2

    Private Sub DataGridView1_CellContentClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView1.CellContentClick

    End Sub

    Private Sub Form2_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'TODO: This line of code loads data into the 'Database151DataSet.Table1' table. You can move, or remove it, as needed.
        Me.Table1TableAdapter.Fill(Me.Database151DataSet.Table1)


    End Sub

    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Table1BindingSource.MovePrevious()
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Table1BindingSource.AddNew()
    End Sub

    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Table1BindingSource.MoveNext()

    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        On Error GoTo Papoy
        If Not IsNumeric(TextBox1.Text) Then

            If Not String.IsNullOrWhiteSpace(TextBox1.Text) And Not String.IsNullOrWhiteSpace(TextBox2.Text) And Not String.IsNullOrWhiteSpace(TextBox3.Text) And Not String.IsNullOrWhiteSpace(TextBox7.Text) Then
                Table1BindingSource.EndEdit()
                Table1TableAdapter.Update(Database151DataSet.Table1)
                MessageBox.Show(" Save Successful")
            Else
                MessageBox.Show("All Fields Required")
            End If
Papoy:
        Else
            MsgBox("INVALID INPUT")
        End If
        Exit Sub
    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        Table1BindingSource.RemoveCurrent()
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        Me.Close()
    End Sub

    Private Sub Button7_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button7.Click
        Form3.Show()
        Me.Hide()

    End Sub

    Private Sub LastnameToolStripButton_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles LastnameToolStripButton.Click
        Try
            Me.Table1TableAdapter.Lastname(Me.Database151DataSet.Table1, LastnameToolStripTextBox.Text)
        Catch ex As System.Exception
            System.Windows.Forms.MessageBox.Show(ex.Message)
        End Try

    End Sub

    Private Sub Button8_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
      
    End Sub

    Private Sub TextBox1_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox1.TextChanged

    End Sub
End Class