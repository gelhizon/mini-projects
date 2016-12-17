Public Class EnrollmentForm
    Dim type As String

    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged
        Me.SectionDropdownTableAdapter.Fill(Me.SchemeDataSet.SectionDropdown, ComboBox1.SelectedValue)
    End Sub

    Private Sub Button1_Click_1(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click

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

        ' If in Add Mode/Type
        If (type = "Adding") Then

            Me.StudentsTableAdapter1.InsertQuery(TextBox4.Text, TextBox1.Text, TextBox2.Text, TextBox3.Text, ComboBox2.SelectedValue)

            MessageBox.Show("Student has been enrolled!", "Enrolled", MessageBoxButtons.OK, MessageBoxIcon.Information)

        Else
            ' If in Update Mode/Type
            StudentsTableAdapter1.UpdateQuery(TextBox2.Text, TextBox3.Text, TextBox1.Text, ComboBox2.SelectedValue, Format(Now, "General date"), Dashboard.DataGridView1.SelectedCells(0).Value)

            MessageBox.Show("Student has been updated!", "Enrolled", MessageBoxButtons.OK, MessageBoxIcon.Information)

        End If

        'Refresh the Data in Dashboard to show new added/updated student
        Dashboard.StudentsTableAdapter.Fill(Dashboard.SchemeDataSet.Students)

        Me.Close()
    End Sub

    ' Clear all textboxes
    Public Sub ClearAll()
        TextBox1.Text = ""
        TextBox2.Text = ""
        TextBox3.Text = ""
        TextBox4.Text = ""
    End Sub

    Public Sub Updating()
        Me.GradeLevelTableAdapter.Fill(Me.SchemeDataSet.GradeLevel)

        Dim row As DataRow = StudentsTableAdapter1.GetDataByID(Dashboard.DataGridView1.SelectedCells(0).Value).Rows(0)

        TextBox4.Text = row("StudentID")
        TextBox1.Text = row("FirstName")
        TextBox2.Text = row("LastName")
        TextBox3.Text = row("MiddleName")

        ComboBox1.SelectedValue = row("GradeLevelID")
        ComboBox2.SelectedValue = row("StudentSectionID")

        Button1.Text = "Update"
        type = "Updating"

    End Sub

    Public Sub Adding()
        ClearAll()

        Me.GradeLevelTableAdapter.Fill(Me.SchemeDataSet.GradeLevel)
        Me.SectionDropdownTableAdapter.Fill(Me.SchemeDataSet.SectionDropdown, ComboBox1.SelectedValue)

        'Get the last ID + 1
        Dim lastID As String = Me.StudentsTableAdapter1.getLastID + 1

        'Affix "0" before ID
        'Example: 00001
        While (lastID.Length < 5)
            lastID = "0" + lastID
        End While

        'Get the Year Today + lastID
        'Example: 2016 + 00001 -> "201600001"
        TextBox4.Text = Date.Today.Year.ToString + lastID

        'Set Button to Enroll
        Button1.Text = "Enroll"
        'Set Type to Adding
        type = "Adding"
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Me.Close()
    End Sub
End Class