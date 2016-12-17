Imports System.Data.SqlClient
Imports System.Data.SQLite

Public Class LoginForm1

    Private Sub OK_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles OK.Click
        Try
            Dim con As New SQLiteConnection(My.Settings.ConnectionString)
            con.Open()
            Dim cmd As SQLiteCommand = con.CreateCommand
            cmd.CommandText = "SELECT FirstName, MiddleName, LastName, Id, Privilege FROM Accounts WHERE Username = @Username AND Password = @Password"
            cmd.Parameters.AddWithValue("@Username", UsernameTextBox.Text)
            cmd.Parameters.AddWithValue("@Password", PasswordTextBox.Text)

            Dim reader As SQLiteDataReader = cmd.ExecuteReader

            If reader.Read Then
                LoginInfo.UserId = Integer.Parse(reader("Id").ToString)
                LoginInfo.FirstName = reader("FirstName").ToString
                LoginInfo.LastName = reader("LastName").ToString
                LoginInfo.MiddleName = reader("MiddleName").ToString

                Me.Hide()
                If reader("Privilege").ToString = "admin" Then
                    AdminForm.Show(Me)
                Else
                    UserForm.Show(Me)
                End If

            Else
                MessageBox.Show("Try Again.")
            End If

            con.Close()
        Catch ex As Exception
            MessageBox.Show(ex.Message)
        End Try

    End Sub

    Private Sub Cancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Cancel.Click
        Me.Close()
    End Sub

End Class
