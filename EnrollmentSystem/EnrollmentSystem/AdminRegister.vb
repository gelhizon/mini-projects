Public Class AdminRegister

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Me.Close()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click

        'Validator 
        'Kung walang laman yung mga textboxes
        Dim tBox
        For Each tBox In Me.Controls
            If TypeOf tBox Is TextBox Then
                If String.IsNullOrWhiteSpace(tBox.Text) Then
                    MessageBox.Show("All fields Required")
                    Exit Sub
                    Exit For
                End If
            End If
        Next

        AdminTableAdapter1.InsertQuery(TextBox5.Text, TextBox4.Text, TextBox3.Text, TextBox1.Text, TextBox2.Text, Format(Now, "General date"), Format(Now, "General date"))

        MessageBox.Show("New user has been added!", "Register", MessageBoxButtons.OK, MessageBoxIcon.Information)

        Me.Close()

    End Sub
End Class