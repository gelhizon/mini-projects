Public Class Form5

    Private Sub AddBindingNavigatorSaveItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.Validate()
        Me.AddBindingSource.EndEdit()
        Me.TableAdapterManager.UpdateAll(Me.Database1DataSet)

    End Sub

    Private Sub Form5_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'TODO: This line of code loads data into the 'Database1DataSet.Add' table. You can move, or remove it, as needed.
        Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)
        'TODO: This line of code loads data into the 'Database1DataSet.Add' table. You can move, or remove it, as needed.
        Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)
        'TODO: This line of code loads data into the 'Database1DataSet.Add' table. You can move, or remove it, as needed.
        Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)
        'TODO: This line of code loads data into the 'Database1DataSet.Add' table. You can move, or remove it, as needed.
        Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)
        'TODO: This line of code loads data into the 'Database1DataSet.Add' table. You can move, or remove it, as needed.
        Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)

    End Sub

    Private Sub AddBindingNavigatorSaveItem_Click_1(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.Validate()
        Me.AddBindingSource.EndEdit()
        Me.TableAdapterManager.UpdateAll(Me.Database1DataSet)

    End Sub

    Private Sub AddBindingNavigatorSaveItem_Click_2(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.Validate()
        Me.AddBindingSource.EndEdit()
        Me.TableAdapterManager.UpdateAll(Me.Database1DataSet)

    End Sub

    Private Sub AddBindingNavigatorSaveItem_Click_3(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.Validate()
        Me.AddBindingSource.EndEdit()
        Me.TableAdapterManager.UpdateAll(Me.Database1DataSet)

    End Sub

    Private Sub AddBindingNavigatorSaveItem_Click_4(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.Validate()
        Me.AddBindingSource.EndEdit()
        Me.TableAdapterManager.UpdateAll(Me.Database1DataSet)

    End Sub

    Private Sub Job_StatusLabel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
End Class