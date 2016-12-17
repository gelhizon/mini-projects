Public Class LoginForm
  
    Private Sub OK_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles OK.Click

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

        ' Get the data using Username and Password
        Dim login = LoginTableAdapter1.GetData(UsernameTextBox.Text, PasswordTextBox.Text)

        ' If Found show Dashboard means username and password match
        If (login.Count > 0) Then
            Dashboard.Show()
            Me.Close()
        Else
            MessageBox.Show("Wrong Username or Password", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If

    End Sub

    Private Sub Cancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Cancel.Click
        Me.Close()
    End Sub

    Private Sub LinkLabel1_LinkClicked(ByVal sender As System.Object, ByVal e As System.Windows.Forms.LinkLabelLinkClickedEventArgs) Handles LinkLabel1.LinkClicked
        AdminRegister.ShowDialog()
    End Sub
End Class
