<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form4
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(Form4))
        Dim Employee_IDLabel As System.Windows.Forms.Label
        Dim First_NameLabel As System.Windows.Forms.Label
        Dim Last_NameLabel As System.Windows.Forms.Label
        Dim Birth_DateLabel As System.Windows.Forms.Label
        Dim AgeLabel As System.Windows.Forms.Label
        Dim StatusLabel As System.Windows.Forms.Label
        Dim GenderLabel As System.Windows.Forms.Label
        Dim NationalityLabel As System.Windows.Forms.Label
        Dim PositionLabel As System.Windows.Forms.Label
        Dim Date_HiredLabel As System.Windows.Forms.Label
        Dim Job_StatusLabel As System.Windows.Forms.Label
        Dim Salary_HourLabel As System.Windows.Forms.Label
        Dim Working_Hours_Pay_PeriodLabel As System.Windows.Forms.Label
        Dim StreetLabel As System.Windows.Forms.Label
        Dim TownLabel As System.Windows.Forms.Label
        Dim MunicipalityLabel As System.Windows.Forms.Label
        Dim Contact_NoLabel As System.Windows.Forms.Label
        Dim EmailLabel As System.Windows.Forms.Label
        Me.Database1DataSet = New WindowsApplication1.Database1DataSet()
        Me.AddBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.AddTableAdapter = New WindowsApplication1.Database1DataSetTableAdapters.AddTableAdapter()
        Me.TableAdapterManager = New WindowsApplication1.Database1DataSetTableAdapters.TableAdapterManager()
        Me.AddBindingNavigator = New System.Windows.Forms.BindingNavigator(Me.components)
        Me.BindingNavigatorMoveFirstItem = New System.Windows.Forms.ToolStripButton()
        Me.BindingNavigatorMovePreviousItem = New System.Windows.Forms.ToolStripButton()
        Me.BindingNavigatorSeparator = New System.Windows.Forms.ToolStripSeparator()
        Me.BindingNavigatorPositionItem = New System.Windows.Forms.ToolStripTextBox()
        Me.BindingNavigatorCountItem = New System.Windows.Forms.ToolStripLabel()
        Me.BindingNavigatorSeparator1 = New System.Windows.Forms.ToolStripSeparator()
        Me.BindingNavigatorMoveNextItem = New System.Windows.Forms.ToolStripButton()
        Me.BindingNavigatorMoveLastItem = New System.Windows.Forms.ToolStripButton()
        Me.BindingNavigatorSeparator2 = New System.Windows.Forms.ToolStripSeparator()
        Me.BindingNavigatorAddNewItem = New System.Windows.Forms.ToolStripButton()
        Me.BindingNavigatorDeleteItem = New System.Windows.Forms.ToolStripButton()
        Me.AddBindingNavigatorSaveItem = New System.Windows.Forms.ToolStripButton()
        Me.Employee_IDTextBox = New System.Windows.Forms.TextBox()
        Me.First_NameTextBox = New System.Windows.Forms.TextBox()
        Me.Last_NameTextBox = New System.Windows.Forms.TextBox()
        Me.Birth_DateTextBox = New System.Windows.Forms.TextBox()
        Me.AgeTextBox = New System.Windows.Forms.TextBox()
        Me.StatusTextBox = New System.Windows.Forms.TextBox()
        Me.GenderTextBox = New System.Windows.Forms.TextBox()
        Me.NationalityTextBox = New System.Windows.Forms.TextBox()
        Me.PositionTextBox = New System.Windows.Forms.TextBox()
        Me.Date_HiredTextBox = New System.Windows.Forms.TextBox()
        Me.Job_StatusTextBox = New System.Windows.Forms.TextBox()
        Me.Salary_HourTextBox = New System.Windows.Forms.TextBox()
        Me.Working_Hours_Pay_PeriodTextBox = New System.Windows.Forms.TextBox()
        Me.StreetTextBox = New System.Windows.Forms.TextBox()
        Me.TownTextBox = New System.Windows.Forms.TextBox()
        Me.MunicipalityTextBox = New System.Windows.Forms.TextBox()
        Me.Contact_NoTextBox = New System.Windows.Forms.TextBox()
        Me.EmailTextBox = New System.Windows.Forms.TextBox()
        Employee_IDLabel = New System.Windows.Forms.Label()
        First_NameLabel = New System.Windows.Forms.Label()
        Last_NameLabel = New System.Windows.Forms.Label()
        Birth_DateLabel = New System.Windows.Forms.Label()
        AgeLabel = New System.Windows.Forms.Label()
        StatusLabel = New System.Windows.Forms.Label()
        GenderLabel = New System.Windows.Forms.Label()
        NationalityLabel = New System.Windows.Forms.Label()
        PositionLabel = New System.Windows.Forms.Label()
        Date_HiredLabel = New System.Windows.Forms.Label()
        Job_StatusLabel = New System.Windows.Forms.Label()
        Salary_HourLabel = New System.Windows.Forms.Label()
        Working_Hours_Pay_PeriodLabel = New System.Windows.Forms.Label()
        StreetLabel = New System.Windows.Forms.Label()
        TownLabel = New System.Windows.Forms.Label()
        MunicipalityLabel = New System.Windows.Forms.Label()
        Contact_NoLabel = New System.Windows.Forms.Label()
        EmailLabel = New System.Windows.Forms.Label()
        CType(Me.Database1DataSet, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.AddBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.AddBindingNavigator, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.AddBindingNavigator.SuspendLayout()
        Me.SuspendLayout()
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
        'AddBindingNavigator
        '
        Me.AddBindingNavigator.AddNewItem = Me.BindingNavigatorAddNewItem
        Me.AddBindingNavigator.BindingSource = Me.AddBindingSource
        Me.AddBindingNavigator.CountItem = Me.BindingNavigatorCountItem
        Me.AddBindingNavigator.DeleteItem = Me.BindingNavigatorDeleteItem
        Me.AddBindingNavigator.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.BindingNavigatorMoveFirstItem, Me.BindingNavigatorMovePreviousItem, Me.BindingNavigatorSeparator, Me.BindingNavigatorPositionItem, Me.BindingNavigatorCountItem, Me.BindingNavigatorSeparator1, Me.BindingNavigatorMoveNextItem, Me.BindingNavigatorMoveLastItem, Me.BindingNavigatorSeparator2, Me.BindingNavigatorAddNewItem, Me.BindingNavigatorDeleteItem, Me.AddBindingNavigatorSaveItem})
        Me.AddBindingNavigator.Location = New System.Drawing.Point(0, 0)
        Me.AddBindingNavigator.MoveFirstItem = Me.BindingNavigatorMoveFirstItem
        Me.AddBindingNavigator.MoveLastItem = Me.BindingNavigatorMoveLastItem
        Me.AddBindingNavigator.MoveNextItem = Me.BindingNavigatorMoveNextItem
        Me.AddBindingNavigator.MovePreviousItem = Me.BindingNavigatorMovePreviousItem
        Me.AddBindingNavigator.Name = "AddBindingNavigator"
        Me.AddBindingNavigator.PositionItem = Me.BindingNavigatorPositionItem
        Me.AddBindingNavigator.Size = New System.Drawing.Size(975, 25)
        Me.AddBindingNavigator.TabIndex = 0
        Me.AddBindingNavigator.Text = "BindingNavigator1"
        '
        'BindingNavigatorMoveFirstItem
        '
        Me.BindingNavigatorMoveFirstItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image
        Me.BindingNavigatorMoveFirstItem.Image = CType(resources.GetObject("BindingNavigatorMoveFirstItem.Image"), System.Drawing.Image)
        Me.BindingNavigatorMoveFirstItem.Name = "BindingNavigatorMoveFirstItem"
        Me.BindingNavigatorMoveFirstItem.RightToLeftAutoMirrorImage = True
        Me.BindingNavigatorMoveFirstItem.Size = New System.Drawing.Size(23, 22)
        Me.BindingNavigatorMoveFirstItem.Text = "Move first"
        '
        'BindingNavigatorMovePreviousItem
        '
        Me.BindingNavigatorMovePreviousItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image
        Me.BindingNavigatorMovePreviousItem.Image = CType(resources.GetObject("BindingNavigatorMovePreviousItem.Image"), System.Drawing.Image)
        Me.BindingNavigatorMovePreviousItem.Name = "BindingNavigatorMovePreviousItem"
        Me.BindingNavigatorMovePreviousItem.RightToLeftAutoMirrorImage = True
        Me.BindingNavigatorMovePreviousItem.Size = New System.Drawing.Size(23, 22)
        Me.BindingNavigatorMovePreviousItem.Text = "Move previous"
        '
        'BindingNavigatorSeparator
        '
        Me.BindingNavigatorSeparator.Name = "BindingNavigatorSeparator"
        Me.BindingNavigatorSeparator.Size = New System.Drawing.Size(6, 25)
        '
        'BindingNavigatorPositionItem
        '
        Me.BindingNavigatorPositionItem.AccessibleName = "Position"
        Me.BindingNavigatorPositionItem.AutoSize = False
        Me.BindingNavigatorPositionItem.Name = "BindingNavigatorPositionItem"
        Me.BindingNavigatorPositionItem.Size = New System.Drawing.Size(50, 23)
        Me.BindingNavigatorPositionItem.Text = "0"
        Me.BindingNavigatorPositionItem.ToolTipText = "Current position"
        '
        'BindingNavigatorCountItem
        '
        Me.BindingNavigatorCountItem.Name = "BindingNavigatorCountItem"
        Me.BindingNavigatorCountItem.Size = New System.Drawing.Size(35, 15)
        Me.BindingNavigatorCountItem.Text = "of {0}"
        Me.BindingNavigatorCountItem.ToolTipText = "Total number of items"
        '
        'BindingNavigatorSeparator1
        '
        Me.BindingNavigatorSeparator1.Name = "BindingNavigatorSeparator"
        Me.BindingNavigatorSeparator1.Size = New System.Drawing.Size(6, 6)
        '
        'BindingNavigatorMoveNextItem
        '
        Me.BindingNavigatorMoveNextItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image
        Me.BindingNavigatorMoveNextItem.Image = CType(resources.GetObject("BindingNavigatorMoveNextItem.Image"), System.Drawing.Image)
        Me.BindingNavigatorMoveNextItem.Name = "BindingNavigatorMoveNextItem"
        Me.BindingNavigatorMoveNextItem.RightToLeftAutoMirrorImage = True
        Me.BindingNavigatorMoveNextItem.Size = New System.Drawing.Size(23, 22)
        Me.BindingNavigatorMoveNextItem.Text = "Move next"
        '
        'BindingNavigatorMoveLastItem
        '
        Me.BindingNavigatorMoveLastItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image
        Me.BindingNavigatorMoveLastItem.Image = CType(resources.GetObject("BindingNavigatorMoveLastItem.Image"), System.Drawing.Image)
        Me.BindingNavigatorMoveLastItem.Name = "BindingNavigatorMoveLastItem"
        Me.BindingNavigatorMoveLastItem.RightToLeftAutoMirrorImage = True
        Me.BindingNavigatorMoveLastItem.Size = New System.Drawing.Size(23, 20)
        Me.BindingNavigatorMoveLastItem.Text = "Move last"
        '
        'BindingNavigatorSeparator2
        '
        Me.BindingNavigatorSeparator2.Name = "BindingNavigatorSeparator"
        Me.BindingNavigatorSeparator2.Size = New System.Drawing.Size(6, 6)
        '
        'BindingNavigatorAddNewItem
        '
        Me.BindingNavigatorAddNewItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image
        Me.BindingNavigatorAddNewItem.Image = CType(resources.GetObject("BindingNavigatorAddNewItem.Image"), System.Drawing.Image)
        Me.BindingNavigatorAddNewItem.Name = "BindingNavigatorAddNewItem"
        Me.BindingNavigatorAddNewItem.RightToLeftAutoMirrorImage = True
        Me.BindingNavigatorAddNewItem.Size = New System.Drawing.Size(23, 22)
        Me.BindingNavigatorAddNewItem.Text = "Add new"
        '
        'BindingNavigatorDeleteItem
        '
        Me.BindingNavigatorDeleteItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image
        Me.BindingNavigatorDeleteItem.Image = CType(resources.GetObject("BindingNavigatorDeleteItem.Image"), System.Drawing.Image)
        Me.BindingNavigatorDeleteItem.Name = "BindingNavigatorDeleteItem"
        Me.BindingNavigatorDeleteItem.RightToLeftAutoMirrorImage = True
        Me.BindingNavigatorDeleteItem.Size = New System.Drawing.Size(23, 20)
        Me.BindingNavigatorDeleteItem.Text = "Delete"
        '
        'AddBindingNavigatorSaveItem
        '
        Me.AddBindingNavigatorSaveItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image
        Me.AddBindingNavigatorSaveItem.Image = CType(resources.GetObject("AddBindingNavigatorSaveItem.Image"), System.Drawing.Image)
        Me.AddBindingNavigatorSaveItem.Name = "AddBindingNavigatorSaveItem"
        Me.AddBindingNavigatorSaveItem.Size = New System.Drawing.Size(23, 23)
        Me.AddBindingNavigatorSaveItem.Text = "Save Data"
        '
        'Employee_IDLabel
        '
        Employee_IDLabel.AutoSize = True
        Employee_IDLabel.Location = New System.Drawing.Point(341, 134)
        Employee_IDLabel.Name = "Employee_IDLabel"
        Employee_IDLabel.Size = New System.Drawing.Size(70, 13)
        Employee_IDLabel.TabIndex = 1
        Employee_IDLabel.Text = "Employee ID:"
        '
        'Employee_IDTextBox
        '
        Me.Employee_IDTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Employee ID", True))
        Me.Employee_IDTextBox.Location = New System.Drawing.Point(484, 131)
        Me.Employee_IDTextBox.Name = "Employee_IDTextBox"
        Me.Employee_IDTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Employee_IDTextBox.TabIndex = 2
        '
        'First_NameLabel
        '
        First_NameLabel.AutoSize = True
        First_NameLabel.Location = New System.Drawing.Point(341, 160)
        First_NameLabel.Name = "First_NameLabel"
        First_NameLabel.Size = New System.Drawing.Size(60, 13)
        First_NameLabel.TabIndex = 3
        First_NameLabel.Text = "First Name:"
        '
        'First_NameTextBox
        '
        Me.First_NameTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "First Name", True))
        Me.First_NameTextBox.Location = New System.Drawing.Point(484, 157)
        Me.First_NameTextBox.Name = "First_NameTextBox"
        Me.First_NameTextBox.Size = New System.Drawing.Size(100, 20)
        Me.First_NameTextBox.TabIndex = 4
        '
        'Last_NameLabel
        '
        Last_NameLabel.AutoSize = True
        Last_NameLabel.Location = New System.Drawing.Point(341, 186)
        Last_NameLabel.Name = "Last_NameLabel"
        Last_NameLabel.Size = New System.Drawing.Size(61, 13)
        Last_NameLabel.TabIndex = 5
        Last_NameLabel.Text = "Last Name:"
        '
        'Last_NameTextBox
        '
        Me.Last_NameTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Last Name", True))
        Me.Last_NameTextBox.Location = New System.Drawing.Point(484, 183)
        Me.Last_NameTextBox.Name = "Last_NameTextBox"
        Me.Last_NameTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Last_NameTextBox.TabIndex = 6
        '
        'Birth_DateLabel
        '
        Birth_DateLabel.AutoSize = True
        Birth_DateLabel.Location = New System.Drawing.Point(341, 212)
        Birth_DateLabel.Name = "Birth_DateLabel"
        Birth_DateLabel.Size = New System.Drawing.Size(57, 13)
        Birth_DateLabel.TabIndex = 7
        Birth_DateLabel.Text = "Birth Date:"
        '
        'Birth_DateTextBox
        '
        Me.Birth_DateTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Birth Date", True))
        Me.Birth_DateTextBox.Location = New System.Drawing.Point(484, 209)
        Me.Birth_DateTextBox.Name = "Birth_DateTextBox"
        Me.Birth_DateTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Birth_DateTextBox.TabIndex = 8
        '
        'AgeLabel
        '
        AgeLabel.AutoSize = True
        AgeLabel.Location = New System.Drawing.Point(341, 238)
        AgeLabel.Name = "AgeLabel"
        AgeLabel.Size = New System.Drawing.Size(29, 13)
        AgeLabel.TabIndex = 9
        AgeLabel.Text = "Age:"
        '
        'AgeTextBox
        '
        Me.AgeTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Age", True))
        Me.AgeTextBox.Location = New System.Drawing.Point(484, 235)
        Me.AgeTextBox.Name = "AgeTextBox"
        Me.AgeTextBox.Size = New System.Drawing.Size(100, 20)
        Me.AgeTextBox.TabIndex = 10
        '
        'StatusLabel
        '
        StatusLabel.AutoSize = True
        StatusLabel.Location = New System.Drawing.Point(341, 264)
        StatusLabel.Name = "StatusLabel"
        StatusLabel.Size = New System.Drawing.Size(40, 13)
        StatusLabel.TabIndex = 11
        StatusLabel.Text = "Status:"
        '
        'StatusTextBox
        '
        Me.StatusTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Status", True))
        Me.StatusTextBox.Location = New System.Drawing.Point(484, 261)
        Me.StatusTextBox.Name = "StatusTextBox"
        Me.StatusTextBox.Size = New System.Drawing.Size(100, 20)
        Me.StatusTextBox.TabIndex = 12
        '
        'GenderLabel
        '
        GenderLabel.AutoSize = True
        GenderLabel.Location = New System.Drawing.Point(341, 290)
        GenderLabel.Name = "GenderLabel"
        GenderLabel.Size = New System.Drawing.Size(45, 13)
        GenderLabel.TabIndex = 13
        GenderLabel.Text = "Gender:"
        '
        'GenderTextBox
        '
        Me.GenderTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Gender", True))
        Me.GenderTextBox.Location = New System.Drawing.Point(484, 287)
        Me.GenderTextBox.Name = "GenderTextBox"
        Me.GenderTextBox.Size = New System.Drawing.Size(100, 20)
        Me.GenderTextBox.TabIndex = 14
        '
        'NationalityLabel
        '
        NationalityLabel.AutoSize = True
        NationalityLabel.Location = New System.Drawing.Point(341, 316)
        NationalityLabel.Name = "NationalityLabel"
        NationalityLabel.Size = New System.Drawing.Size(59, 13)
        NationalityLabel.TabIndex = 15
        NationalityLabel.Text = "Nationality:"
        '
        'NationalityTextBox
        '
        Me.NationalityTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Nationality", True))
        Me.NationalityTextBox.Location = New System.Drawing.Point(484, 313)
        Me.NationalityTextBox.Name = "NationalityTextBox"
        Me.NationalityTextBox.Size = New System.Drawing.Size(100, 20)
        Me.NationalityTextBox.TabIndex = 16
        '
        'PositionLabel
        '
        PositionLabel.AutoSize = True
        PositionLabel.Location = New System.Drawing.Point(341, 342)
        PositionLabel.Name = "PositionLabel"
        PositionLabel.Size = New System.Drawing.Size(47, 13)
        PositionLabel.TabIndex = 17
        PositionLabel.Text = "Position:"
        '
        'PositionTextBox
        '
        Me.PositionTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Position", True))
        Me.PositionTextBox.Location = New System.Drawing.Point(484, 339)
        Me.PositionTextBox.Name = "PositionTextBox"
        Me.PositionTextBox.Size = New System.Drawing.Size(100, 20)
        Me.PositionTextBox.TabIndex = 18
        '
        'Date_HiredLabel
        '
        Date_HiredLabel.AutoSize = True
        Date_HiredLabel.Location = New System.Drawing.Point(341, 368)
        Date_HiredLabel.Name = "Date_HiredLabel"
        Date_HiredLabel.Size = New System.Drawing.Size(61, 13)
        Date_HiredLabel.TabIndex = 19
        Date_HiredLabel.Text = "Date Hired:"
        '
        'Date_HiredTextBox
        '
        Me.Date_HiredTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Date Hired", True))
        Me.Date_HiredTextBox.Location = New System.Drawing.Point(484, 365)
        Me.Date_HiredTextBox.Name = "Date_HiredTextBox"
        Me.Date_HiredTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Date_HiredTextBox.TabIndex = 20
        '
        'Job_StatusLabel
        '
        Job_StatusLabel.AutoSize = True
        Job_StatusLabel.Location = New System.Drawing.Point(341, 394)
        Job_StatusLabel.Name = "Job_StatusLabel"
        Job_StatusLabel.Size = New System.Drawing.Size(60, 13)
        Job_StatusLabel.TabIndex = 21
        Job_StatusLabel.Text = "Job Status:"
        '
        'Job_StatusTextBox
        '
        Me.Job_StatusTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Job Status", True))
        Me.Job_StatusTextBox.Location = New System.Drawing.Point(484, 391)
        Me.Job_StatusTextBox.Name = "Job_StatusTextBox"
        Me.Job_StatusTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Job_StatusTextBox.TabIndex = 22
        '
        'Salary_HourLabel
        '
        Salary_HourLabel.AutoSize = True
        Salary_HourLabel.Location = New System.Drawing.Point(341, 420)
        Salary_HourLabel.Name = "Salary_HourLabel"
        Salary_HourLabel.Size = New System.Drawing.Size(67, 13)
        Salary_HourLabel.TabIndex = 23
        Salary_HourLabel.Text = "Salary/Hour:"
        '
        'Salary_HourTextBox
        '
        Me.Salary_HourTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Salary/Hour", True))
        Me.Salary_HourTextBox.Location = New System.Drawing.Point(484, 417)
        Me.Salary_HourTextBox.Name = "Salary_HourTextBox"
        Me.Salary_HourTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Salary_HourTextBox.TabIndex = 24
        '
        'Working_Hours_Pay_PeriodLabel
        '
        Working_Hours_Pay_PeriodLabel.AutoSize = True
        Working_Hours_Pay_PeriodLabel.Location = New System.Drawing.Point(341, 446)
        Working_Hours_Pay_PeriodLabel.Name = "Working_Hours_Pay_PeriodLabel"
        Working_Hours_Pay_PeriodLabel.Size = New System.Drawing.Size(137, 13)
        Working_Hours_Pay_PeriodLabel.TabIndex = 25
        Working_Hours_Pay_PeriodLabel.Text = "Working Hours/Pay Period:"
        '
        'Working_Hours_Pay_PeriodTextBox
        '
        Me.Working_Hours_Pay_PeriodTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Working Hours/Pay Period", True))
        Me.Working_Hours_Pay_PeriodTextBox.Location = New System.Drawing.Point(484, 443)
        Me.Working_Hours_Pay_PeriodTextBox.Name = "Working_Hours_Pay_PeriodTextBox"
        Me.Working_Hours_Pay_PeriodTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Working_Hours_Pay_PeriodTextBox.TabIndex = 26
        '
        'StreetLabel
        '
        StreetLabel.AutoSize = True
        StreetLabel.Location = New System.Drawing.Point(341, 472)
        StreetLabel.Name = "StreetLabel"
        StreetLabel.Size = New System.Drawing.Size(38, 13)
        StreetLabel.TabIndex = 27
        StreetLabel.Text = "Street:"
        '
        'StreetTextBox
        '
        Me.StreetTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Street", True))
        Me.StreetTextBox.Location = New System.Drawing.Point(484, 469)
        Me.StreetTextBox.Name = "StreetTextBox"
        Me.StreetTextBox.Size = New System.Drawing.Size(100, 20)
        Me.StreetTextBox.TabIndex = 28
        '
        'TownLabel
        '
        TownLabel.AutoSize = True
        TownLabel.Location = New System.Drawing.Point(341, 498)
        TownLabel.Name = "TownLabel"
        TownLabel.Size = New System.Drawing.Size(37, 13)
        TownLabel.TabIndex = 29
        TownLabel.Text = "Town:"
        '
        'TownTextBox
        '
        Me.TownTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Town", True))
        Me.TownTextBox.Location = New System.Drawing.Point(484, 495)
        Me.TownTextBox.Name = "TownTextBox"
        Me.TownTextBox.Size = New System.Drawing.Size(100, 20)
        Me.TownTextBox.TabIndex = 30
        '
        'MunicipalityLabel
        '
        MunicipalityLabel.AutoSize = True
        MunicipalityLabel.Location = New System.Drawing.Point(341, 524)
        MunicipalityLabel.Name = "MunicipalityLabel"
        MunicipalityLabel.Size = New System.Drawing.Size(65, 13)
        MunicipalityLabel.TabIndex = 31
        MunicipalityLabel.Text = "Municipality:"
        '
        'MunicipalityTextBox
        '
        Me.MunicipalityTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Municipality", True))
        Me.MunicipalityTextBox.Location = New System.Drawing.Point(484, 521)
        Me.MunicipalityTextBox.Name = "MunicipalityTextBox"
        Me.MunicipalityTextBox.Size = New System.Drawing.Size(100, 20)
        Me.MunicipalityTextBox.TabIndex = 32
        '
        'Contact_NoLabel
        '
        Contact_NoLabel.AutoSize = True
        Contact_NoLabel.Location = New System.Drawing.Point(341, 550)
        Contact_NoLabel.Name = "Contact_NoLabel"
        Contact_NoLabel.Size = New System.Drawing.Size(64, 13)
        Contact_NoLabel.TabIndex = 33
        Contact_NoLabel.Text = "Contact No:"
        '
        'Contact_NoTextBox
        '
        Me.Contact_NoTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Contact No", True))
        Me.Contact_NoTextBox.Location = New System.Drawing.Point(484, 547)
        Me.Contact_NoTextBox.Name = "Contact_NoTextBox"
        Me.Contact_NoTextBox.Size = New System.Drawing.Size(100, 20)
        Me.Contact_NoTextBox.TabIndex = 34
        '
        'EmailLabel
        '
        EmailLabel.AutoSize = True
        EmailLabel.Location = New System.Drawing.Point(341, 576)
        EmailLabel.Name = "EmailLabel"
        EmailLabel.Size = New System.Drawing.Size(35, 13)
        EmailLabel.TabIndex = 35
        EmailLabel.Text = "Email:"
        '
        'EmailTextBox
        '
        Me.EmailTextBox.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.AddBindingSource, "Email", True))
        Me.EmailTextBox.Location = New System.Drawing.Point(484, 573)
        Me.EmailTextBox.Name = "EmailTextBox"
        Me.EmailTextBox.Size = New System.Drawing.Size(100, 20)
        Me.EmailTextBox.TabIndex = 36
        '
        'Form4
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(975, 613)
        Me.Controls.Add(Employee_IDLabel)
        Me.Controls.Add(Me.Employee_IDTextBox)
        Me.Controls.Add(First_NameLabel)
        Me.Controls.Add(Me.First_NameTextBox)
        Me.Controls.Add(Last_NameLabel)
        Me.Controls.Add(Me.Last_NameTextBox)
        Me.Controls.Add(Birth_DateLabel)
        Me.Controls.Add(Me.Birth_DateTextBox)
        Me.Controls.Add(AgeLabel)
        Me.Controls.Add(Me.AgeTextBox)
        Me.Controls.Add(StatusLabel)
        Me.Controls.Add(Me.StatusTextBox)
        Me.Controls.Add(GenderLabel)
        Me.Controls.Add(Me.GenderTextBox)
        Me.Controls.Add(NationalityLabel)
        Me.Controls.Add(Me.NationalityTextBox)
        Me.Controls.Add(PositionLabel)
        Me.Controls.Add(Me.PositionTextBox)
        Me.Controls.Add(Date_HiredLabel)
        Me.Controls.Add(Me.Date_HiredTextBox)
        Me.Controls.Add(Job_StatusLabel)
        Me.Controls.Add(Me.Job_StatusTextBox)
        Me.Controls.Add(Salary_HourLabel)
        Me.Controls.Add(Me.Salary_HourTextBox)
        Me.Controls.Add(Working_Hours_Pay_PeriodLabel)
        Me.Controls.Add(Me.Working_Hours_Pay_PeriodTextBox)
        Me.Controls.Add(StreetLabel)
        Me.Controls.Add(Me.StreetTextBox)
        Me.Controls.Add(TownLabel)
        Me.Controls.Add(Me.TownTextBox)
        Me.Controls.Add(MunicipalityLabel)
        Me.Controls.Add(Me.MunicipalityTextBox)
        Me.Controls.Add(Contact_NoLabel)
        Me.Controls.Add(Me.Contact_NoTextBox)
        Me.Controls.Add(EmailLabel)
        Me.Controls.Add(Me.EmailTextBox)
        Me.Controls.Add(Me.AddBindingNavigator)
        Me.Name = "Form4"
        Me.Text = "Form4"
        CType(Me.Database1DataSet, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.AddBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.AddBindingNavigator, System.ComponentModel.ISupportInitialize).EndInit()
        Me.AddBindingNavigator.ResumeLayout(False)
        Me.AddBindingNavigator.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents Database1DataSet As WindowsApplication1.Database1DataSet
    Friend WithEvents AddBindingSource As System.Windows.Forms.BindingSource
    Friend WithEvents AddTableAdapter As WindowsApplication1.Database1DataSetTableAdapters.AddTableAdapter
    Friend WithEvents TableAdapterManager As WindowsApplication1.Database1DataSetTableAdapters.TableAdapterManager
    Friend WithEvents AddBindingNavigator As System.Windows.Forms.BindingNavigator
    Friend WithEvents BindingNavigatorAddNewItem As System.Windows.Forms.ToolStripButton
    Friend WithEvents BindingNavigatorCountItem As System.Windows.Forms.ToolStripLabel
    Friend WithEvents BindingNavigatorDeleteItem As System.Windows.Forms.ToolStripButton
    Friend WithEvents BindingNavigatorMoveFirstItem As System.Windows.Forms.ToolStripButton
    Friend WithEvents BindingNavigatorMovePreviousItem As System.Windows.Forms.ToolStripButton
    Friend WithEvents BindingNavigatorSeparator As System.Windows.Forms.ToolStripSeparator
    Friend WithEvents BindingNavigatorPositionItem As System.Windows.Forms.ToolStripTextBox
    Friend WithEvents BindingNavigatorSeparator1 As System.Windows.Forms.ToolStripSeparator
    Friend WithEvents BindingNavigatorMoveNextItem As System.Windows.Forms.ToolStripButton
    Friend WithEvents BindingNavigatorMoveLastItem As System.Windows.Forms.ToolStripButton
    Friend WithEvents BindingNavigatorSeparator2 As System.Windows.Forms.ToolStripSeparator
    Friend WithEvents AddBindingNavigatorSaveItem As System.Windows.Forms.ToolStripButton
    Friend WithEvents Employee_IDTextBox As System.Windows.Forms.TextBox
    Friend WithEvents First_NameTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Last_NameTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Birth_DateTextBox As System.Windows.Forms.TextBox
    Friend WithEvents AgeTextBox As System.Windows.Forms.TextBox
    Friend WithEvents StatusTextBox As System.Windows.Forms.TextBox
    Friend WithEvents GenderTextBox As System.Windows.Forms.TextBox
    Friend WithEvents NationalityTextBox As System.Windows.Forms.TextBox
    Friend WithEvents PositionTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Date_HiredTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Job_StatusTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Salary_HourTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Working_Hours_Pay_PeriodTextBox As System.Windows.Forms.TextBox
    Friend WithEvents StreetTextBox As System.Windows.Forms.TextBox
    Friend WithEvents TownTextBox As System.Windows.Forms.TextBox
    Friend WithEvents MunicipalityTextBox As System.Windows.Forms.TextBox
    Friend WithEvents Contact_NoTextBox As System.Windows.Forms.TextBox
    Friend WithEvents EmailTextBox As System.Windows.Forms.TextBox
End Class
