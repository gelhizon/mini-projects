Imports System.Data.SqlClient
Imports System.Data.SQLite

Public Class AdminForm

    Private Sub AdminForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        ToolStripStatusLabel2.Text = LoginInfo.FirstName & " " & LoginInfo.MiddleName & " " & LoginInfo.LastName
        loadOrders()
    End Sub

    Private Sub Button6_Click(sender As Object, e As EventArgs) Handles Button6.Click
        LoginForm1.UsernameTextBox.Text = String.Empty
        LoginForm1.PasswordTextBox.Text = String.Empty
        LoginForm1.Show()
        LoginForm1.UsernameTextBox.Focus()
        Me.Close()
    End Sub

    Private Sub Button4_Click(sender As Object, e As EventArgs) Handles Button4.Click
        Try
            Dim con As New SQLiteConnection(My.Settings.ConnectionString)

            con.Open()

            For Each row As DataGridViewRow In DataGridView1.SelectedRows
                Dim cmd As SQLiteCommand = con.CreateCommand
                cmd.CommandText = "UPDATE Orders SET isAccepted = 1 WHERE Id = @Id; UPDATE Items SET Stock = Stock - @Stock WHERE Id = @ItemId"
                cmd.Parameters.AddWithValue("@Id", row.Cells("OrderId").Value)
                cmd.Parameters.AddWithValue("@Stock", row.Cells("Quantity").Value)
                cmd.Parameters.AddWithValue("@ItemId", row.Cells("ItemId").Value)
                cmd.ExecuteNonQuery()
            Next

            MessageBox.Show("Success")
            loadOrders()
            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        End Try

    End Sub

    Private Sub loadOrders()
        DataGridView1.Rows.Clear()
        Try
            Dim con As New SQLiteConnection(My.Settings.ConnectionString)
            con.Open()
            Dim cmd As SQLiteCommand = con.CreateCommand
            cmd.CommandText = "SELECT Orders.Id, Orders.ItemId, Orders.UserId, Orders.Quantity, Items.Product, Items.Description, Items.Price, Items.Stock, Items.Preview, Accounts.Username, Accounts.Password, Accounts.FirstName, Accounts.MiddleName, Accounts.LastName, Orders.isAccepted FROM Orders INNER JOIN Items ON Orders.ItemId = Items.Id INNER JOIN Accounts ON Orders.UserId = Accounts.Id WHERE Orders.isAccepted = 0"

            Dim reader As SQLiteDataReader = cmd.ExecuteReader


            While reader.Read

                DataGridView1.Rows.Add(reader("Id"), reader("ItemId"), reader("UserId"), reader("FirstName").ToString & " " & reader("MiddleName").ToString & " " & reader("LastName").ToString, Image.FromFile(My.Application.Info.DirectoryPath & "\resources\" & reader("preview").ToString), reader("Product"), reader("Description"), reader("Quantity"), reader("Price"), reader("Stock"))
            End While

            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        End Try
    End Sub

End Class
