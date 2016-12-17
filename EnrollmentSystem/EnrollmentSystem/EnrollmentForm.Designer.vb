<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class EnrollmentForm
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Button1 = New System.Windows.Forms.Button()
        Me.Button2 = New System.Windows.Forms.Button()
        Me.TextBox1 = New System.Windows.Forms.TextBox()
        Me.TextBox2 = New System.Windows.Forms.TextBox()
        Me.TextBox3 = New System.Windows.Forms.TextBox()
        Me.ComboBox1 = New System.Windows.Forms.ComboBox()
        Me.GradeLevelBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.SchemeDataSet = New EnrollmenSystem.SchemeDataSet()
        Me.ComboBox2 = New System.Windows.Forms.ComboBox()
        Me.SectionDropdownBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.SectionTableAdapter = New EnrollmenSystem.SchemeDataSetTableAdapters.SectionTableAdapter()
        Me.GradeLevelTableAdapter = New EnrollmenSystem.SchemeDataSetTableAdapters.GradeLevelTableAdapter()
        Me.SectionDropdownTableAdapter = New EnrollmenSystem.SchemeDataSetTableAdapters.SectionDropdownTableAdapter()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.TextBox4 = New System.Windows.Forms.TextBox()
        Me.StudentsTableAdapter1 = New EnrollmenSystem.SchemeDataSetTableAdapters.StudentsTableAdapter()
        Me.StudentsBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.TableAdapterManager = New EnrollmenSystem.SchemeDataSetTableAdapters.TableAdapterManager()
        CType(Me.GradeLevelBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.SchemeDataSet, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.SectionDropdownBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.StudentsBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'Label1
        '
        Me.Label1.Location = New System.Drawing.Point(12, 49)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(74, 29)
        Me.Label1.TabIndex = 0
        Me.Label1.Text = "Last Name:"
        Me.Label1.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'Label2
        '
        Me.Label2.Location = New System.Drawing.Point(12, 78)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(74, 29)
        Me.Label2.TabIndex = 1
        Me.Label2.Text = "First Name:"
        Me.Label2.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'Label3
        '
        Me.Label3.Location = New System.Drawing.Point(12, 107)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(74, 29)
        Me.Label3.TabIndex = 2
        Me.Label3.Text = "Middle Name:"
        Me.Label3.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'Label4
        '
        Me.Label4.Location = New System.Drawing.Point(12, 136)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(74, 29)
        Me.Label4.TabIndex = 3
        Me.Label4.Text = "Grade:"
        Me.Label4.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'Label5
        '
        Me.Label5.Location = New System.Drawing.Point(12, 165)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(74, 29)
        Me.Label5.TabIndex = 4
        Me.Label5.Text = "Section:"
        Me.Label5.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'Button1
        '
        Me.Button1.Location = New System.Drawing.Point(170, 208)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(75, 23)
        Me.Button1.TabIndex = 6
        Me.Button1.Text = "Enroll"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'Button2
        '
        Me.Button2.Location = New System.Drawing.Point(251, 208)
        Me.Button2.Name = "Button2"
        Me.Button2.Size = New System.Drawing.Size(75, 23)
        Me.Button2.TabIndex = 7
        Me.Button2.Text = "Cancel"
        Me.Button2.UseVisualStyleBackColor = True
        '
        'TextBox1
        '
        Me.TextBox1.Location = New System.Drawing.Point(92, 54)
        Me.TextBox1.Name = "TextBox1"
        Me.TextBox1.ScrollBars = System.Windows.Forms.ScrollBars.Both
        Me.TextBox1.Size = New System.Drawing.Size(234, 20)
        Me.TextBox1.TabIndex = 1
        '
        'TextBox2
        '
        Me.TextBox2.Location = New System.Drawing.Point(92, 83)
        Me.TextBox2.Name = "TextBox2"
        Me.TextBox2.ScrollBars = System.Windows.Forms.ScrollBars.Both
        Me.TextBox2.Size = New System.Drawing.Size(234, 20)
        Me.TextBox2.TabIndex = 2
        '
        'TextBox3
        '
        Me.TextBox3.Location = New System.Drawing.Point(92, 112)
        Me.TextBox3.Name = "TextBox3"
        Me.TextBox3.ScrollBars = System.Windows.Forms.ScrollBars.Both
        Me.TextBox3.Size = New System.Drawing.Size(234, 20)
        Me.TextBox3.TabIndex = 3
        '
        'ComboBox1
        '
        Me.ComboBox1.DataSource = Me.GradeLevelBindingSource
        Me.ComboBox1.DisplayMember = "GradeName"
        Me.ComboBox1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.ComboBox1.FormattingEnabled = True
        Me.ComboBox1.Location = New System.Drawing.Point(92, 141)
        Me.ComboBox1.Name = "ComboBox1"
        Me.ComboBox1.Size = New System.Drawing.Size(234, 21)
        Me.ComboBox1.TabIndex = 4
        Me.ComboBox1.ValueMember = "ID"
        '
        'GradeLevelBindingSource
        '
        Me.GradeLevelBindingSource.DataMember = "GradeLevel"
        Me.GradeLevelBindingSource.DataSource = Me.SchemeDataSet
        '
        'SchemeDataSet
        '
        Me.SchemeDataSet.DataSetName = "SchemeDataSet"
        Me.SchemeDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'ComboBox2
        '
        Me.ComboBox2.DataSource = Me.SectionDropdownBindingSource
        Me.ComboBox2.DisplayMember = "SectionName"
        Me.ComboBox2.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.ComboBox2.FormattingEnabled = True
        Me.ComboBox2.Location = New System.Drawing.Point(92, 170)
        Me.ComboBox2.Name = "ComboBox2"
        Me.ComboBox2.Size = New System.Drawing.Size(234, 21)
        Me.ComboBox2.TabIndex = 5
        Me.ComboBox2.ValueMember = "ID"
        '
        'SectionDropdownBindingSource
        '
        Me.SectionDropdownBindingSource.DataMember = "SectionDropdown"
        Me.SectionDropdownBindingSource.DataSource = Me.SchemeDataSet
        '
        'SectionTableAdapter
        '
        Me.SectionTableAdapter.ClearBeforeFill = True
        '
        'GradeLevelTableAdapter
        '
        Me.GradeLevelTableAdapter.ClearBeforeFill = True
        '
        'SectionDropdownTableAdapter
        '
        Me.SectionDropdownTableAdapter.ClearBeforeFill = True
        '
        'Label6
        '
        Me.Label6.Location = New System.Drawing.Point(12, 19)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(74, 29)
        Me.Label6.TabIndex = 12
        Me.Label6.Text = "Student ID"
        Me.Label6.TextAlign = System.Drawing.ContentAlignment.MiddleRight
        '
        'TextBox4
        '
        Me.TextBox4.Location = New System.Drawing.Point(92, 24)
        Me.TextBox4.Name = "TextBox4"
        Me.TextBox4.ReadOnly = True
        Me.TextBox4.ScrollBars = System.Windows.Forms.ScrollBars.Both
        Me.TextBox4.Size = New System.Drawing.Size(234, 20)
        Me.TextBox4.TabIndex = 13
        '
        'StudentsTableAdapter1
        '
        Me.StudentsTableAdapter1.ClearBeforeFill = True
        '
        'StudentsBindingSource
        '
        Me.StudentsBindingSource.DataMember = "Students"
        Me.StudentsBindingSource.DataSource = Me.SchemeDataSet
        '
        'TableAdapterManager
        '
        Me.TableAdapterManager.AdminTableAdapter = Nothing
        Me.TableAdapterManager.BackupDataSetBeforeUpdate = False
        Me.TableAdapterManager.GradeLevelTableAdapter = Me.GradeLevelTableAdapter
        Me.TableAdapterManager.LoginTableAdapter = Nothing
        Me.TableAdapterManager.SectionDropdownTableAdapter = Me.SectionDropdownTableAdapter
        Me.TableAdapterManager.SectionTableAdapter = Me.SectionTableAdapter
        Me.TableAdapterManager.UpdateOrder = EnrollmenSystem.SchemeDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete
        '
        'EnrollmentForm
        '
        Me.AcceptButton = Me.Button1
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(346, 251)
        Me.Controls.Add(Me.TextBox4)
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.ComboBox2)
        Me.Controls.Add(Me.ComboBox1)
        Me.Controls.Add(Me.TextBox3)
        Me.Controls.Add(Me.TextBox2)
        Me.Controls.Add(Me.TextBox1)
        Me.Controls.Add(Me.Button2)
        Me.Controls.Add(Me.Button1)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Name = "EnrollmentForm"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Enrollment Form"
        CType(Me.GradeLevelBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.SchemeDataSet, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.SectionDropdownBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.StudentsBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents Button2 As System.Windows.Forms.Button
    Friend WithEvents TextBox1 As System.Windows.Forms.TextBox
    Friend WithEvents TextBox2 As System.Windows.Forms.TextBox
    Friend WithEvents TextBox3 As System.Windows.Forms.TextBox
    Friend WithEvents ComboBox1 As System.Windows.Forms.ComboBox
    Friend WithEvents ComboBox2 As System.Windows.Forms.ComboBox
    Friend WithEvents SectionTableAdapter As EnrollmenSystem.SchemeDataSetTableAdapters.SectionTableAdapter
    Friend WithEvents SchemeDataSet As EnrollmenSystem.SchemeDataSet
    Friend WithEvents GradeLevelBindingSource As System.Windows.Forms.BindingSource
    Friend WithEvents GradeLevelTableAdapter As EnrollmenSystem.SchemeDataSetTableAdapters.GradeLevelTableAdapter
    Friend WithEvents SectionDropdownBindingSource As System.Windows.Forms.BindingSource
    Friend WithEvents SectionDropdownTableAdapter As EnrollmenSystem.SchemeDataSetTableAdapters.SectionDropdownTableAdapter
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents TextBox4 As System.Windows.Forms.TextBox
    Friend WithEvents StudentsTableAdapter1 As EnrollmenSystem.SchemeDataSetTableAdapters.StudentsTableAdapter
    Friend WithEvents StudentsBindingSource As System.Windows.Forms.BindingSource
    Friend WithEvents TableAdapterManager As EnrollmenSystem.SchemeDataSetTableAdapters.TableAdapterManager
End Class
