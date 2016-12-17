Public Class Dashboard


    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'TODO: This line of code loads data into the 'SchemeDataSet.GradeLevel' table. You can move, or remove it, as needed.
        Me.GradeLevelTableAdapter.Fill(Me.SchemeDataSet.GradeLevel)
        ComboBox1.SelectedIndex = -1

        'TODO: This line of code loads data into the 'SchemeDataSet.Students' table. You can move, or remove it, as needed.
        Me.StudentsTableAdapter.Fill(Me.SchemeDataSet.Students)

    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click

        'Set Enrollment Form to Add Mode 
        EnrollmentForm.Adding()
        EnrollmentForm.ShowDialog()

    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click

        'Set Enrollment Form to Update Mode
        EnrollmentForm.Updating()
        EnrollmentForm.ShowDialog()

    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click

        'Show Warning Before Deleting
        If (MessageBox.Show("Are you want to delete?", "Delete", MessageBoxButtons.YesNo, MessageBoxIcon.Warning) = DialogResult.Yes) Then

            Me.StudentsTableAdapter.DeleteQuery(DataGridView1.SelectedCells(0).Value)
            Me.StudentsTableAdapter.Fill(Me.SchemeDataSet.Students)

        End If

    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged

        FilterData()

    End Sub

    Private Sub LinkLabel1_LinkClicked(ByVal sender As System.Object, ByVal e As System.Windows.Forms.LinkLabelLinkClickedEventArgs) Handles LinkLabel1.LinkClicked

        'Remove SelectedItem in Combobox
        ComboBox1.SelectedIndex = -1

    End Sub

    Private Sub TextBox1_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox1.TextChanged

        FilterData()

    End Sub

    Private Sub FilterData()
        
        'If there is selected in combobox
        If (Not ComboBox1.SelectedIndex = -1) Then
            StudentsBindingSource.Filter = "GradeID = " & ComboBox1.SelectedValue & " AND (LastName LIKE '%" & TextBox1.Text & "%' OR FirstName LIKE '%" & TextBox1.Text & "%' OR MiddleName LIKE '%" & TextBox1.Text & "%')"
        Else
            'If nothing is selected in grade combobox 
            StudentsBindingSource.Filter = "LastName LIKE '%" & TextBox1.Text & "%' OR FirstName LIKE '%" & TextBox1.Text & "%' OR MiddleName LIKE '%" & TextBox1.Text & "%'"
        End If

    End Sub

End Class
