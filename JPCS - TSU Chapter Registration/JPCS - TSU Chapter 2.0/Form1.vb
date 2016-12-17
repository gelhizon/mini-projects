Imports System.Data.SqlClient
Imports Microsoft.VisualBasic
Public Class Form1
    Dim connectionString As String = "Data Source=.;Database=JPCS - TSU CHAPTER;User ID=jpcs;Password=juniorpcs"
    Dim con As New SqlConnection(connectionString)
    Dim cmd As New SqlCommand
    Dim dr As SqlDataReader

    Dim DOB As String

    Dim selec As String = "SELECT * FROM ChapterMembers"
    Dim adap As New SqlDataAdapter(selec, con)
    Dim ds As New DataSet
    Dim sTable As DataTable

    Dim count As Integer = 0
    Dim Msg As Integer
    Dim selecfee As String = "SELECT * FROM StudentsFeeFirst"
    Dim adapfee As New SqlDataAdapter(selecfee, con)
    Dim dsfee As New DataSet
    Dim sTablefee As DataTable

    Dim selecfee2 As String = "SELECT * FROM StudentsFeeSecond"
    Dim adapfee2 As New SqlDataAdapter(selecfee2, con)
    Dim dsfee2 As New DataSet
    Dim sTablefee2 As DataTable

    Dim selecfee3 As String = "SELECT * FROM StudentsFeeThird"
    Dim adapfee3 As New SqlDataAdapter(selecfee3, con)
    Dim dsfee3 As New DataSet
    Dim sTablefee3 As DataTable

    Dim selecfee4 As String = "SELECT * FROM StudentsFeeFourth"
    Dim adapfee4 As New SqlDataAdapter(selecfee4, con)
    Dim dsfee4 As New DataSet
    Dim sTablefee4 As DataTable
    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        DateTimePicker2.Hide()
        'Label19.Hide()
        Label20.Hide()
        TextBox1.Hide()
        TextBox2.Hide()
        TextBox3.Hide()
        D()
        D1()
        D2()
        D3()
        D4()
        Me.Anchor = AnchorStyles.Bottom Or _
        AnchorStyles.Left Or AnchorStyles.Right Or _
        AnchorStyles.Top
        cb1.DropDownStyle = ComboBoxStyle.DropDownList
        cb2.DropDownStyle = ComboBoxStyle.DropDownList
        cb3.DropDownStyle = ComboBoxStyle.DropDownList
        ComboBox1.DropDownStyle = ComboBoxStyle.DropDownList
        ComboBox2.DropDownStyle = ComboBoxStyle.DropDownList
        ComboBox3.DropDownStyle = ComboBoxStyle.DropDownList
    End Sub
    Public Sub RefreshData()

        sTable.Clear()
        adap.Fill(sTable)



    End Sub
    Private Sub D()
        cmd.Connection = con
        cmd.CommandText = selec
        con.Open()
        adap.Fill(ds, "tablemember")
        sTable = ds.Tables("tablemember")
        con.Close()
    End Sub
    Private Sub D1()

        cmd.Connection = con
        cmd.CommandText = selecfee
        con.Open()
        adapfee.Fill(dsfee, "tablefee1")
        sTablefee = dsfee.Tables("tablefee1")
        con.Close()
        


    End Sub
    Private Sub D2()

        cmd.Connection = con
        cmd.CommandText = selecfee2
        con.Open()
        adapfee2.Fill(dsfee2, "tablefee2")
        sTablefee2 = dsfee2.Tables("tablefee2")
        con.Close()
        

    End Sub
    Private Sub D3()

        cmd.Connection = con
        cmd.CommandText = selecfee3
        con.Open()
        adapfee3.Fill(dsfee3, "tablefee3")
        sTablefee3 = dsfee3.Tables("tablefee3")
        con.Close()
       


    End Sub
    Private Sub D4()

        cmd.Connection = con
        cmd.CommandText = selecfee4
        con.Open()
        adapfee4.Fill(dsfee4, "tablefee4")
        sTablefee4 = dsfee4.Tables("tablefee4")
        con.Close()
       


    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        DOB = ComboBox1.Text + " " + ComboBox2.Text + "," + " " + ComboBox3.Text


        Try

            If t1.Text <> "" And t2.Text <> "" And t3.Text <> "" And t4.Text <> "" And cb1.Text <> "" And DOB <> "" And cb2.Text <> "" And cb3.Text <> "" And t8.Text <> "" And t9.Text <> "" And t10.Text <> "" And ComboBox1.Text <> "" And ComboBox2.Text <> "" And ComboBox3.Text <> "" Then
                If Not IsNumeric(t1.Text) Then
                    MsgBox("ERROR AT NUMBER")

                ElseIf IsNumeric(t2.Text) Then
                    MsgBox("ERROR AT SURNAME")

                ElseIf IsNumeric(t3.Text) Then
                    MsgBox("ERROR AT FIRSTNAME")

                ElseIf IsNumeric(t4.Text) Then
                    MsgBox("ERROR AT MIDDLENAME")

                ElseIf Not IsNumeric(t8.Text) Then
                    MsgBox("ERROR AT LINE NUMBER")

                Else
                    InsertIntoAllMembers()
                End If
            Else
                MsgBox("FILL UP ALL FIELDS")
            End If

        Catch ex As Exception
            MsgBox("UNDONE OPERATION PLEASE RESTART THE APPLICATION,(REGISTER)")
        End Try

    End Sub
    Private Sub InsertIntoAllMembers()
        TextBox1.Text = 0
        TextBox2.Text = 0
        TextBox3.Text = 0
        Try
            con.Open()
            
            cmd.CommandText = "select Number from ChapterMembers WHERE  Number ='" & t1.Text & "' "

            dr = cmd.ExecuteReader
            If dr.HasRows Then
                MsgBox("NUMBER ALREADY USED")
            Else

                con.Close()
                Msg = MsgBox("SAVED?", vbYesNo + vbEmpty, "JUNIOR PHILIPPINE COMPUTER SOCIETY")
                If Msg = 6 Then

                    con.Open()
                    cmd.CommandText = "Insert into ChapterMembers (Number,LastName,FirstName,MiddleName,Gender,BirthDate,Course,Year,Graduating,HomeAddress,EmailAddress,AreaCode,LineNumber,Date)values('" & t1.Text.TrimEnd & "','" & t2.Text.ToUpper.TrimEnd & "','" & t3.Text.ToUpper.TrimEnd & "','" & t4.Text.ToUpper.TrimEnd & "','" & cb1.SelectedItem & "','" & DOB & "','" & t66.Text.TrimEnd & "','" & cb2.SelectedItem & "','" & cb3.SelectedItem & "','" & t9.Text.ToUpper.TrimEnd & "','" & t10.Text.Trim & "','" & t77.Text.TrimEnd & "','" & t8.Text.TrimEnd & "','" & DateTimePicker2.Text.TrimEnd & "')"
                    cmd.ExecuteNonQuery()
                    con.Close()
                    Choose()

                End If

            End If
            con.Close()
            Me.RefreshData()
        Catch ex As Exception
            MsgBox("UNDONE OPERATION PLEASE RESTART THE APPLICATION,(SAVING)")
        End Try
    End Sub
    Private Sub Counter()

        Dim NatnlFee As Integer = 0
        Dim OrgFee As Integer = 0
        Dim students As Integer = 0

        count = Val(TextBox1.Text) + 1
        NatnlFee = count * 100
        OrgFee = count * 50
        TextBox1.Text = count
        TextBox2.Text = NatnlFee
        TextBox3.Text = OrgFee



    End Sub
    Private Sub CounterMinus()

        Dim NatnlFee As Integer = 0
        Dim OrgFee As Integer = 0
        Dim students As Integer = 0
        count = Val(TextBox1.Text) - 1


        NatnlFee = Val(TextBox2.Text) - 100
        OrgFee = Val(TextBox3.Text) - 50
        TextBox1.Text = count
        TextBox2.Text = NatnlFee
        TextBox3.Text = OrgFee


    End Sub
    Private Sub First()

        Dim ccount As Integer = dr(0)
        Dim Natfee As Integer = dr(1)
        Dim Orgfee As Integer = dr(2)
        TextBox1.Text = ccount
        TextBox2.Text = Natfee
        TextBox3.Text = Orgfee
        Try

            Counter()
            con.Close()
            con.Open()
            cmd.CommandText = "UPDATE StudentsFeeFirst set [No. Of Students] ='" & TextBox1.Text & "',[National Fee]='" & TextBox2.Text & "', [Org. Fee] = '" & TextBox3.Text & "' WHERE Date = '" & DateTimePicker2.Text & "' "
            cmd.ExecuteNonQuery()
            RefreshData1()
            con.Close()

        Catch ex As Exception
            MsgBox("UNDONE OPERATION PLEASE RESTART THE APPLICATION,(UPDATE FIRST YEAR)")
        End Try
    End Sub
    Private Sub Second()
        Dim ccount As Integer = dr(0)
        Dim Natfee As Integer = dr(1)
        Dim Orgfee As Integer = dr(2)
        TextBox1.Text = ccount
        TextBox2.Text = Natfee
        TextBox3.Text = Orgfee
        Try

            Counter()
            con.Close()
            con.Open()
            cmd.CommandText = "UPDATE StudentsFeeSecond set [No. Of Students] ='" & TextBox1.Text & "',[National Fee]='" & TextBox2.Text & "', [Org. Fee] = '" & TextBox3.Text & "' WHERE Date = '" & DateTimePicker2.Text & "' "
            cmd.ExecuteNonQuery()
            RefreshData2()
            con.Close()

        Catch ex As Exception
            MsgBox("UNDONE OPERATION PLEASE RESTART THE APPLICATION,(UPDATE SECOND YEAR)")
        End Try
    End Sub
    Private Sub Third()
        Dim ccount As Integer = dr(0)
        Dim Natfee As Integer = dr(1)
        Dim Orgfee As Integer = dr(2)
        TextBox1.Text = ccount
        TextBox2.Text = Natfee
        TextBox3.Text = Orgfee
        Try

            Counter()
            con.Close()
            con.Open()
            cmd.CommandText = "UPDATE StudentsFeeThird set [No. Of Students] ='" & TextBox1.Text & "',[National Fee]='" & TextBox2.Text & "', [Org. Fee] = '" & TextBox3.Text & "' WHERE Date = '" & DateTimePicker2.Text & "' "
            cmd.ExecuteNonQuery()
            RefreshData3()
            con.Close()

        Catch ex As Exception
            MsgBox("UNDONE OPERATION PLEASE RESTART THE APPLICATION,(UPDATE THIRD YEAR)")
        End Try
    End Sub
    Private Sub Fourth()
        Dim ccount As Integer = dr(0)
        Dim Natfee As Integer = dr(1)
        Dim Orgfee As Integer = dr(2)
        TextBox1.Text = ccount
        TextBox2.Text = Natfee
        TextBox3.Text = Orgfee
        Try
            Counter()
            con.Close()
            con.Open()
            cmd.CommandText = "UPDATE StudentsFeeFourth set [No. Of Students] ='" & TextBox1.Text & "',[National Fee]='" & TextBox2.Text & "', [Org. Fee] = '" & TextBox3.Text & "' WHERE Date = '" & DateTimePicker2.Text & "' "
            cmd.ExecuteNonQuery()
            RefreshData4()
            con.Close()

        Catch ex As Exception
            MsgBox("UNDONE OPERATION PLEASE RESTART THE APPLICATION,(UPDATE FOURTH YEAR)")
        End Try

    End Sub
    Private Sub Choose()
        Try
            If cb2.SelectedIndex = 0 Then

                con.Open()
                cmd.CommandText = "select * from StudentsFeeFirst WHERE  Date ='@a' "
                Dim param As SqlParameter = cmd.Parameters(0)
                param.Value = "test"

                dr = cmd.ExecuteReader
                If dr.Read Then
                    First()
                Else
                    Counter()
                    con.Close()
                    con.Open()
                    cmd.CommandText = "Insert into StudentsFeeFirst ([No. Of Students],[National Fee],[Org. Fee],date,Year)values('" & TextBox1.Text & "','" & TextBox2.Text & "','" & TextBox3.Text & "','" & DateTimePicker2.Text & "','" & cb2.SelectedItem & "')"
                    cmd.ExecuteNonQuery()
                    RefreshData1()
                    con.Close()

                End If

            ElseIf cb2.SelectedIndex = 1 Then

                con.Open()
                cmd.CommandText = "select * from StudentsFeeSecond WHERE  Date ='" & DateTimePicker2.Text & "' "
                dr = cmd.ExecuteReader
                If dr.Read Then
                    Second()
                Else
                    Counter()
                    con.Close()
                    con.Open()
                    cmd.CommandText = "Insert into StudentsFeeSecond ([No. Of Students],[National Fee],[Org. Fee],date,Year)values('" & TextBox1.Text & "','" & TextBox2.Text & "','" & TextBox3.Text & "','" & DateTimePicker2.Text & "','" & cb2.SelectedItem & "')"
                    cmd.ExecuteNonQuery()
                    RefreshData2()
                    con.Close()
                End If

            ElseIf cb2.SelectedIndex = 2 Then

                con.Open()
                cmd.CommandText = "select * from StudentsFeeThird WHERE  Date ='" & DateTimePicker2.Text & "' "
                dr = cmd.ExecuteReader
                If dr.Read Then
                    Third()
                Else
                    Counter()
                    con.Close()
                    con.Open()
                    cmd.CommandText = "Insert into StudentsFeeThird ([No. Of Students],[National Fee],[Org. Fee],date,Year)values('" & TextBox1.Text & "','" & TextBox2.Text & "','" & TextBox3.Text & "','" & DateTimePicker2.Text & "','" & cb2.SelectedItem & "')"
                    cmd.ExecuteNonQuery()
                    RefreshData3()
                    con.Close()
                End If


            ElseIf cb2.SelectedIndex = 3 Then

                con.Open()
                cmd.CommandText = "select * from StudentsFeeFourth WHERE  Date ='" & DateTimePicker2.Text & "' "
                dr = cmd.ExecuteReader
                If dr.Read Then
                    Fourth()
                Else

                    Counter()
                    MsgBox("AFTER COUNT")
                    con.Close()
                    con.Open()
                    MsgBox("OPEN")
                    cmd.CommandText = "Insert into StudentsFeeFourth ([No. Of Students],[National Fee],[Org. Fee],date,Year)values('" & TextBox1.Text & "','" & TextBox2.Text & "','" & TextBox3.Text & "','" & DateTimePicker2.Text & "','" & cb2.SelectedItem & "')"
                    cmd.ExecuteNonQuery()
                    RefreshData4()
                    MsgBox("SAVE")
                    con.Close()
                End If
            End If

        Catch ex As Exception
            MsgBox("UNDONE OPERATION PLEASE RESTART THE APPLICATION,(SAVE STUDENTS FEE YEAR)")
        End Try
        TextBox1.Clear()
        TextBox2.Clear()
        TextBox3.Clear()
        Clear()
        Me.Close()
    End Sub
    
    Public Sub RefreshData1()
        sTablefee.Clear()
        adapfee.Fill(sTablefee)

    End Sub
    Public Sub RefreshData2()
        sTablefee2.Clear()
        adapfee2.Fill(sTablefee2)

    End Sub
    Public Sub RefreshData3()
        sTablefee3.Clear()
        adapfee3.Fill(sTablefee3)

    End Sub
    Public Sub RefreshData4()
        sTablefee4.Clear()
        adapfee4.Fill(sTablefee4)

    End Sub
    Private Sub PaymentsToolStripMenuItem1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub t2_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles t2.KeyPress
        If Asc(e.KeyChar) <> 32 And Asc(e.KeyChar) <> 8 And Asc(e.KeyChar) < 65 Or Asc(e.KeyChar) > 90 And Asc(e.KeyChar) < 97 Or Asc(e.KeyChar) > 122 Then
            e.Handled = True
        End If
    End Sub

    Private Sub t3_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles t3.KeyPress
        If Asc(e.KeyChar) <> 32 And Asc(e.KeyChar) <> 8 And Asc(e.KeyChar) < 65 Or Asc(e.KeyChar) > 90 And Asc(e.KeyChar) < 97 Or Asc(e.KeyChar) > 122 Then
            e.Handled = True
        End If
    End Sub

    Private Sub t4_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles t4.KeyPress
        If Asc(e.KeyChar) <> 32 And Asc(e.KeyChar) <> 8 And Asc(e.KeyChar) < 65 Or Asc(e.KeyChar) > 90 And Asc(e.KeyChar) < 97 Or Asc(e.KeyChar) > 122 Then
            e.Handled = True
        End If
    End Sub

    Private Sub t8_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles t8.KeyPress
        If Asc(e.KeyChar) <> 32 And Asc(e.KeyChar) <> 8 And Asc(e.KeyChar) <> 48 And Asc(e.KeyChar) <> 49 And Asc(e.KeyChar) <> 50 And Asc(e.KeyChar) <> 51 And Asc(e.KeyChar) <> 52 And Asc(e.KeyChar) <> 53 And Asc(e.KeyChar) <> 54 And Asc(e.KeyChar) <> 55 And Asc(e.KeyChar) <> 56 And Asc(e.KeyChar) <> 57 Then
            e.Handled = True
        End If
    End Sub

    Private Sub t1_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles t1.KeyPress
        If Asc(e.KeyChar) <> 32 And Asc(e.KeyChar) <> 8 And Asc(e.KeyChar) <> 48 And Asc(e.KeyChar) <> 49 And Asc(e.KeyChar) <> 50 And Asc(e.KeyChar) <> 51 And Asc(e.KeyChar) <> 52 And Asc(e.KeyChar) <> 53 And Asc(e.KeyChar) <> 54 And Asc(e.KeyChar) <> 55 And Asc(e.KeyChar) <> 56 And Asc(e.KeyChar) <> 57 Then
            e.Handled = True
        End If
    End Sub
    Private Sub Clear()
        Try
            t1.Clear()
            t2.Clear()
            t3.Clear()
            t4.Clear()
            ComboBox1.Text = ""
            ComboBox2.Text = ""
            ComboBox3.Text = ""

            cb1.Text = ""
            cb2.Text = ""
            cb3.Text = ""
            t8.Clear()
            t9.Clear()
            t10.Clear()
        Catch ex As Exception
            MsgBox("Please Restart the Program")
        End Try
    End Sub

    Private Sub t2_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles t2.TextChanged

    End Sub

    Private Sub t5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub

    Private Sub TextBox2_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox2.TextChanged

    End Sub
End Class
