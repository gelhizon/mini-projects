Imports System.Windows.Forms
Imports System.Data.SqlClient
Imports System.Data.SQLite

Public Class MyOrders

    Private Sub OK_Button_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles OK_Button.Click
        Me.DialogResult = System.Windows.Forms.DialogResult.OK
        Me.Close()
    End Sub

    Private Sub Cancel_Button_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Cancel_Button.Click
        Me.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.Close()
    End Sub

    Private Sub Dialog1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        DataGridView1.Rows.Clear()

        Try
            Dim con As New SQLiteConnection(My.Settings.ConnectionString)
            con.Open()
            Dim cmd As SQLiteCommand = con.CreateCommand
            cmd.CommandText = "SELECT Orders.Id, Orders.ItemId, Orders.Quantity, Orders.isAccepted, Items.Product, Items.Preview FROM Orders INNER JOIN Items ON Orders.ItemId = Items.Id WHERE (Orders.UserId = @UserId)"
            cmd.Parameters.AddWithValue("@UserId", LoginInfo.UserId)

            Dim reader As SQLiteDataReader = cmd.ExecuteReader

            While reader.Read
                Dim statusString As String = "Pending"
                If reader("isAccepted").ToString = "1" Then
                    statusString = "Accepted"
                End If

                DataGridView1.Rows.Add(reader("Id"), reader("ItemId"), Image.FromFile(My.Application.Info.DirectoryPath & "\resources\" & reader("Preview").ToString), reader("Product"), reader("Quantity"), statusString)
            End While

            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        End Try
    End Sub
End Class
