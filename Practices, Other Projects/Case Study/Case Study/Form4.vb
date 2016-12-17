Public Class Form4

    Private Sub AddBindingNavigatorSaveItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AddBindingNavigatorSaveItem.Click
        Me.Validate()
        Me.AddBindingSource.EndEdit()
        Me.TableAdapterManager.UpdateAll(Me.Database1DataSet)

    End Sub

    Private Sub Form4_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'TODO: This line of code loads data into the 'Database1DataSet.Add' table. You can move, or remove it, as needed.
        Me.AddTableAdapter.Fill(Me.Database1DataSet.Add)

    End Sub

    Private Sub BindingNavigatorMoveNextItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles BindingNavigatorMoveNextItem.Click

    End Sub
End Class