<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form5
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
        Dim Employee_IDLabel As System.Windows.Forms.Label
        Dim First_NameLabel As System.Windows.Forms.Label
        Dim Last_NameLabel As System.Windows.Forms.Label
        Dim PositionLabel As System.Windows.Forms.Label
        Dim Date_HiredLabel As System.Windows.Forms.Label
        Dim Job_StatusLabel As System.Windows.Forms.Label
        Dim Salary_HourLabel As System.Windows.Forms.Label
        Dim Working_Hours_Pay_PeriodLabel As System.Windows.Forms.Label
        Dim Contact_NoLabel As System.Windows.Forms.Label
        Dim EmailLabel As System.Windows.Forms.Label
        Dim Employee_IDLabel1 As System.Windows.Forms.Label
        Me.Panel2 = New System.Windows.Forms.Panel()
        Me.Database1DataSet = New WindowsApplication1.Database1DataSet()
        Me.AddBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.AddTableAdapter = New WindowsApplication1.Database1DataSetTableAdapters.AddTableAdapter()
        Me.TableAdapterManager = New WindowsApplication1.Database1DataSetTableAdapters.TableAdapterManager()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.Employee_IDTextBox = New System.Windows.Forms.TextBox()
        Me.First_NameTextBox = New System.Windows.Forms.TextBox()
        Me.Last_NameTextBox = New System.Windows.Forms.TextBox()
        Me.PositionTextBox = New System.Windows.Forms.TextBox()
        Me.Date_HiredTextBox = New System.Windows.Forms.TextBox()
        Me.Job_StatusTextBox = New System.Windows.Forms.TextBox()
        Me.Salary_HourTextBox = New System.Windows.Forms.TextBox()
        Me.Working_Hours_Pay_PeriodTextBox = New System.Windows.Forms.TextBox()
        Me.Contact_NoTextBox = New System.Windows.Forms.TextBox()
        Me.EmailTextBox = New System.Windows.Forms.TextBox()
        Me.Employee_IDTextBox1 = New System.Windows.Forms.TextBox()
        Me.Button1 = New System.Windows.Forms.Button()
        Me.Button2 = New System.Windows.Forms.Button()
        Employee_IDLabel = New System.Windows.Forms.Label()
        First_NameLabel = New System.Windows.Forms.Label()
        Last_NameLabel = New System.Windows.Forms.Label()
        PositionLabel = New System.Windows.Forms.Label()
        Date_HiredLabel = New System.Windows.Forms.Label()
        Job_StatusLabel = New System.Windows.Forms.Label()
        Salary_HourLabel = New System.Windows.Forms.Label()
        Working_Hours_Pay_PeriodLabel = New System.Windows.Forms.Label()
        Contact_NoLabel = New System.Windows.Forms.Label()
        EmailLabel = New System.Windows.Forms.Label()
        Employee_IDLabel1 = New System.Windows.Forms.Label()
        CType(Me.Database1DataSet, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.AddBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.GroupBox1.SuspendLayout()
        Me.SuspendLayout()
        '
        'Panel2
        '
        Me.Panel2.BackgroundImage = Global.WindowsApplication1.My.Resources.Resources._7header
        Me.Panel2.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch
        Me.Panel2.Location = New System.Drawing.Point(2, 12)
        Me.Panel2.Name = "Panel2"
        Me.Panel2.Size = New System.Drawing.Size(1101, 74)
        Me.Panel2.TabIndex = 2
        '
        'Database1DataSet
        '
        Me.Database1DataSet.DataSetName = "Database1DataSet"
        Me.Database1DataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'AddBindingSource
        '
        Me.AddBindingSource.DataMember = "Add"
        Me.AddBindingSource.DataSource = Me.Database1DataSet
        '
        'AddTableAdapter
        '
        Me.AddTableAdapter.ClearBeforeFill = True
        '
        'TableAdapterManager
        '
        Me.TableAdapterManager.AddTableAdapter = Me.AddTableAdapter
        Me.TableAdapterManager.BackupDataSetBeforeUpdate = False
        Me.TableAdapterManager.UpdateOrder = WindowsApplication1.Database1DataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Employee_IDLabel1)
        Me.GroupBox1.Controls.Add(Me.Employee_IDTextBox1)
        Me.GroupBox1.Controls.Add(Employee_IDLabel)
        Me.GroupBox1.Controls.Add(Me.Employee_IDTextBox)
        Me.GroupBox1.Controls.Add(First_NameLabel)
        Me.GroupBox1.Controls.Add(Me.First_NameTextBox)
        Me.GroupBox1.Controls.Add(Last_NameLabel)
        Me.GroupBox1.Controls.Add(Me.Last_NameTextBox)
        Me.GroupBox1.Controls.Add(PositionLabel)
        Me.GroupBox1.Controls.Add(Me.PositionTextBox)
        Me.GroupBox1.Controls.Add(Date_HiredLabel)
        Me.GroupBox1.Controls.Add(Me.Date_HiredTextBox)
        Me.GroupBox1.Controls.Add(Job_StatusLabel)
        Me.GroupBox1.Controls.Add(Me.Job_StatusTextBox)
        Me.GroupBox1.Controls.Add(Salary_HourLabel)
        Me.GroupBox1.Controls.Add(Me.Salary_HourTextBox)
        Me.GroupBox1.Controls.Add(Working_Hours_Pay_PeriodLabel)
        Me.GroupBox1.Controls.Add(Me.Working_Hours_Pay_PeriodTextBox)
        Me.GroupBox1.Controls.Add(Contact_NoLabel)
        Me.GroupBox1.Controls.Add(Me.Contact_NoTextBox)
        Me.GroupBox1.Controls.Add(EmailLabel)
        Me.GroupBox1.Controls.Add(Me.EmailTextBox)
        Me.GroupBox1.Location = New System.Drawing.Point(55, 169)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(1037, 196)
        Me.GroupBox1.TabIndex = 3
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "Employee Information"
        '
        'Employee_IDLabel
        '
        Employee_IDLabel.AutoSize = True
        Employee_IDLabel.Location = New System.Drawing.Point(-127, 15)
        Employee_IDLabel.Name = "Employee_IDLabel"
        Employee_IDLabel.Size = New System.Drawing.Size(70, 13)
        Employee_IDLabel.TabIndex = 36
        Employee_IDLabel.Text = "Employee ID:"
        '
        'Employee_IDTextBox
        '
        Me.Employee_IDTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.Employee_IDTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Employee_IDTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Employee ID", True))
        Me.Employee_IDTextBox.Location = New System.Drawing.Point(-17, 15)
        Me.Employee_IDTextBox.Name = "Employee_IDTextBox"
        Me.Employee_IDTextBox.Size = New System.Drawing.Size(100, 13)
        Me.Employee_IDTextBox.TabIndex = 37
        '
        'First_NameLabel
        '
        First_NameLabel.AutoSize = True
        First_NameLabel.Location = New System.Drawing.Point(-113, 56)
        First_NameLabel.Name = "First_NameLabel"
        First_NameLabel.Size = New System.Drawing.Size(60, 13)
        First_NameLabel.TabIndex = 38
        First_NameLabel.Text = "First Name:"
        '
        'First_NameTextBox
        '
        Me.First_NameTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.First_NameTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.First_NameTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "First Name", True))
        Me.First_NameTextBox.Location = New System.Drawing.Point(-17, 58)
        Me.First_NameTextBox.Name = "First_NameTextBox"
        Me.First_NameTextBox.Size = New System.Drawing.Size(100, 13)
        Me.First_NameTextBox.TabIndex = 39
        '
        'Last_NameLabel
        '
        Last_NameLabel.AutoSize = True
        Last_NameLabel.Location = New System.Drawing.Point(89, 56)
        Last_NameLabel.Name = "Last_NameLabel"
        Last_NameLabel.Size = New System.Drawing.Size(61, 13)
        Last_NameLabel.TabIndex = 40
        Last_NameLabel.Text = "Last Name:"
        '
        'Last_NameTextBox
        '
        Me.Last_NameTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.Last_NameTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Last_NameTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Last Name", True))
        Me.Last_NameTextBox.Location = New System.Drawing.Point(185, 57)
        Me.Last_NameTextBox.Name = "Last_NameTextBox"
        Me.Last_NameTextBox.Size = New System.Drawing.Size(100, 13)
        Me.Last_NameTextBox.TabIndex = 41
        '
        'PositionLabel
        '
        PositionLabel.AutoSize = True
        PositionLabel.Location = New System.Drawing.Point(317, 56)
        PositionLabel.Name = "PositionLabel"
        PositionLabel.Size = New System.Drawing.Size(47, 13)
        PositionLabel.TabIndex = 42
        PositionLabel.Text = "Position:"
        '
        'PositionTextBox
        '
        Me.PositionTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.PositionTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.PositionTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Position", True))
        Me.PositionTextBox.Location = New System.Drawing.Point(392, 58)
        Me.PositionTextBox.Name = "PositionTextBox"
        Me.PositionTextBox.Size = New System.Drawing.Size(100, 13)
        Me.PositionTextBox.TabIndex = 43
        '
        'Date_HiredLabel
        '
        Date_HiredLabel.AutoSize = True
        Date_HiredLabel.Location = New System.Drawing.Point(531, 56)
        Date_HiredLabel.Name = "Date_HiredLabel"
        Date_HiredLabel.Size = New System.Drawing.Size(61, 13)
        Date_HiredLabel.TabIndex = 44
        Date_HiredLabel.Text = "Date Hired:"
        '
        'Date_HiredTextBox
        '
        Me.Date_HiredTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.Date_HiredTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Date_HiredTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Date Hired", True))
        Me.Date_HiredTextBox.Location = New System.Drawing.Point(627, 56)
        Me.Date_HiredTextBox.Name = "Date_HiredTextBox"
        Me.Date_HiredTextBox.Size = New System.Drawing.Size(100, 13)
        Me.Date_HiredTextBox.TabIndex = 45
        '
        'Job_StatusLabel
        '
        Job_StatusLabel.AutoSize = True
        Job_StatusLabel.Location = New System.Drawing.Point(531, 90)
        Job_StatusLabel.Name = "Job_StatusLabel"
        Job_StatusLabel.Size = New System.Drawing.Size(60, 13)
        Job_StatusLabel.TabIndex = 46
        Job_StatusLabel.Text = "Job Status:"
        '
        'Job_StatusTextBox
        '
        Me.Job_StatusTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.Job_StatusTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Job_StatusTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Job Status", True))
        Me.Job_StatusTextBox.Location = New System.Drawing.Point(627, 91)
        Me.Job_StatusTextBox.Name = "Job_StatusTextBox"
        Me.Job_StatusTextBox.Size = New System.Drawing.Size(100, 13)
        Me.Job_StatusTextBox.TabIndex = 47
        '
        'Salary_HourLabel
        '
        Salary_HourLabel.AutoSize = True
        Salary_HourLabel.Location = New System.Drawing.Point(144, 131)
        Salary_HourLabel.Name = "Salary_HourLabel"
        Salary_HourLabel.Size = New System.Drawing.Size(67, 13)
        Salary_HourLabel.TabIndex = 48
        Salary_HourLabel.Text = "Salary/Hour:"
        '
        'Salary_HourTextBox
        '
        Me.Salary_HourTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.Salary_HourTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Salary_HourTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Salary/Hour", True))
        Me.Salary_HourTextBox.Location = New System.Drawing.Point(241, 135)
        Me.Salary_HourTextBox.Name = "Salary_HourTextBox"
        Me.Salary_HourTextBox.Size = New System.Drawing.Size(100, 13)
        Me.Salary_HourTextBox.TabIndex = 49
        '
        'Working_Hours_Pay_PeriodLabel
        '
        Working_Hours_Pay_PeriodLabel.AutoSize = True
        Working_Hours_Pay_PeriodLabel.Location = New System.Drawing.Point(144, 157)
        Working_Hours_Pay_PeriodLabel.Name = "Working_Hours_Pay_PeriodLabel"
        Working_Hours_Pay_PeriodLabel.Size = New System.Drawing.Size(137, 13)
        Working_Hours_Pay_PeriodLabel.TabIndex = 50
        Working_Hours_Pay_PeriodLabel.Text = "Working Hours/Pay Period:"
        '
        'Working_Hours_Pay_PeriodTextBox
        '
        Me.Working_Hours_Pay_PeriodTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.Working_Hours_Pay_PeriodTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Working_Hours_Pay_PeriodTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Working Hours/Pay Period", True))
        Me.Working_Hours_Pay_PeriodTextBox.Location = New System.Drawing.Point(347, 157)
        Me.Working_Hours_Pay_PeriodTextBox.Name = "Working_Hours_Pay_PeriodTextBox"
        Me.Working_Hours_Pay_PeriodTextBox.Size = New System.Drawing.Size(100, 13)
        Me.Working_Hours_Pay_PeriodTextBox.TabIndex = 51
        '
        'Contact_NoLabel
        '
        Contact_NoLabel.AutoSize = True
        Contact_NoLabel.Location = New System.Drawing.Point(-116, 91)
        Contact_NoLabel.Name = "Contact_NoLabel"
        Contact_NoLabel.Size = New System.Drawing.Size(64, 13)
        Contact_NoLabel.TabIndex = 52
        Contact_NoLabel.Text = "Contact No:"
        '
        'Contact_NoTextBox
        '
        Me.Contact_NoTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.Contact_NoTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Contact_NoTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Contact No", True))
        Me.Contact_NoTextBox.Location = New System.Drawing.Point(-17, 92)
        Me.Contact_NoTextBox.Name = "Contact_NoTextBox"
        Me.Contact_NoTextBox.Size = New System.Drawing.Size(100, 13)
        Me.Contact_NoTextBox.TabIndex = 53
        '
        'EmailLabel
        '
        EmailLabel.AutoSize = True
        EmailLabel.Location = New System.Drawing.Point(110, 91)
        EmailLabel.Name = "EmailLabel"
        EmailLabel.Size = New System.Drawing.Size(35, 13)
        EmailLabel.TabIndex = 54
        EmailLabel.Text = "Email:"
        '
        'EmailTextBox
        '
        Me.EmailTextBox.BackColor = System.Drawing.SystemColors.Control
        Me.EmailTextBox.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.EmailTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Email", True))
        Me.EmailTextBox.Location = New System.Drawing.Point(168, 92)
        Me.EmailTextBox.Name = "EmailTextBox"
        Me.EmailTextBox.Size = New System.Drawing.Size(100, 13)
        Me.EmailTextBox.TabIndex = 55
        '
        'Employee_IDLabel1
        '
        Employee_IDLabel1.AutoSize = True
        Employee_IDLabel1.Location = New System.Drawing.Point(22, 31)
        Employee_IDLabel1.Name = "Employee_IDLabel1"
        Employee_IDLabel1.Size = New System.Drawing.Size(70, 13)
        Employee_IDLabel1.TabIndex = 55
        Employee_IDLabel1.Text = "Employee ID:"
        '
        'Employee_IDTextBox1
        '
        Me.Employee_IDTextBox1.BackColor = System.Drawing.SystemColors.Control
        Me.Employee_IDTextBox1.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.Employee_IDTextBox1.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Employee ID", True))
        Me.Employee_IDTextBox1.Location = New System.Drawing.Point(98, 28)
        Me.Employee_IDTextBox1.Name = "Employee_IDTextBox1"
        Me.Employee_IDTextBox1.Size = New System.Drawing.Size(100, 13)
        Me.Employee_IDTextBox1.TabIndex = 56
        '
        'Button1
        '
        Me.Button1.Location = New System.Drawing.Point(798, 648)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(75, 44)
        Me.Button1.TabIndex = 4
        Me.Button1.Text = "Create Payroll"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'Button2
        '
        Me.Button2.Location = New System.Drawing.Point(899, 648)
        Me.Button2.Name = "Button2"
        Me.Button2.Size = New System.Drawing.Size(75, 44)
        Me.Button2.TabIndex = 5
        Me.Button2.Text = "Cancel"
        Me.Button2.UseVisualStyleBackColor = True
        '
        'Form5
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.AutoScroll = True
        Me.ClientSize = New System.Drawing.Size(1120, 749)
        Me.Controls.Add(Me.Button2)
        Me.Controls.Add(Me.Button1)
        Me.Controls.Add(Me.GroupBox1)
        Me.Controls.Add(Me.Panel2)
        Me.Name = "Form5"
        Me.Text = "Form5"
        CType(Me.Database1DataSet, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.AddBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents Panel2 As System.Windows.Forms.Panel
    Friend WithEvents Database1DataSet As WindowsApplication1.Database1DataSet
    Friend WithEvents AddBindingSource As System.Windows.Forms.BindingSource
    Friend WithEvents AddTableAdapter As WindowsApplication1.Database1DataSetTableAdapters.AddTableAdapter
    Friend WithEvents TableAdapterManager As WindowsApplication1.Database1DataSetTableAdapters.TableAdapterManager
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents Employee_IDTextBox1 As System.Windows.Forms.TextBox
    Friend WithEvents Employee_IDTextBox As System.Windows.Forms.TextBox
    Friend WithEvents First_NameTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Last_NameTextBox As System.Windows.Forms.TextBox
    Friend WithEvents PositionTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Date_HiredTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Job_StatusTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Salary_HourTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Working_Hours_Pay_PeriodTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Contact_NoTextBox As System.Windows.Forms.TextBox
    Friend WithEvents EmailTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents Button2 As System.Windows.Forms.Button
End Class
