Imports System.Data.SqlClient
Imports System.Data.SQLite

Public Class UserForm

    Private Sub UserForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        ToolStripStatusLabel2.Text = LoginInfo.FirstName & " " & LoginInfo.MiddleName & " " & LoginInfo.LastName
        DataGridView1.Rows.Clear()
        Try
            Dim con As New SQLiteConnection(My.Settings.ConnectionString)
            Dim cmd As SQLiteCommand = con.CreateCommand
            con.Open()
            cmd.CommandText = "SELECT Id, Product, Description, Price, Stock, Preview FROM Items"
            Dim reader As SQLiteDataReader = cmd.ExecuteReader

            While reader.Read
                DataGridView1.Rows.Add(reader("Id"), Image.FromFile(My.Application.Info.DirectoryPath & "\resources\" & reader("Preview").ToString), reader("Product"), reader("Description"), reader("Price"), reader("Stock"))
            End While

            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        End Try
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        For Each row As DataGridViewRow In DataGridView1.SelectedRows
            DataGridView2.Rows.Add(row.Cells("ItemId").Value, row.Cells("Product").Value, NumericUpDown1.Value.ToString)

            Label4.Text = CStr(Double.Parse(Label4.Text) + Double.Parse(row.Cells("Price").Value.ToString) * Double.Parse(NumericUpDown1.Value.ToString))
        Next
        NumericUpDown1.Value = 1
    End Sub

    Private Sub Button4_Click(sender As Object, e As EventArgs) Handles Button4.Click
        Try
            Dim con As New SQLiteConnection(My.Settings.ConnectionString)
            con.Open()

            For Each row As DataGridViewRow In DataGridView2.Rows
                Dim cmd As SQLiteCommand = con.CreateCommand
                cmd.CommandText = "INSERT INTO Orders (ItemId, UserId, Quantity, isAccepted) VALUES (@ItemId, @UserId,  @Quantity, 0)"

                cmd.Parameters.AddWithValue("@ItemId", row.Cells("DataGridViewTextBoxColumn1").Value)
                cmd.Parameters.AddWithValue("@UserId", LoginInfo.UserId)
                cmd.Parameters.AddWithValue("@Quantity", row.Cells("Quantity").Value)

                cmd.ExecuteNonQuery()
            Next

            NumericUpDown1.Value = 1
            Label4.Text = "0"
            DataGridView2.Rows.Clear()
            MessageBox.Show("Sucesfully Submitted")
            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        End Try

    End Sub

    Private Sub Button6_Click(sender As Object, e As EventArgs) Handles Button6.Click
        LoginForm1.UsernameTextBox.Text = String.Empty
        LoginForm1.PasswordTextBox.Text = String.Empty
        LoginForm1.Show()
        LoginForm1.UsernameTextBox.Focus()
        Me.Close()
    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        MyOrders.ShowDialog(Me)
    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles Button3.Click
        DataGridView2.Rows.Clear()
        Label4.Text = String.Empty
        NumericUpDown1.Value = 1
    End Sub
End Class