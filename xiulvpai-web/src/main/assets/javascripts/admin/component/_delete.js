function initDeleteButton(){
    console.debug("init delete button start");
    $('.delete-btn').click((e) => {
        e.preventDefault();
        debugger;
        const href = $(e.currentTarget).data().href;
        const deleteForm = `
            <div id="deleteModal" class="modal">
                <form action="${href}" method="post">
                    <input type="hidden" name="_method" value="delete">
                    <div class="modal-content">
                      <h4>提示</h4>
                      <p>确定要删除该记录吗？</p>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="modal-close waves-effect waves-green btn-flat">取消</button>
                      <button type="submit" class="modal-close waves-effect waves-green btn-flat">确定</button>
                    </div>
                </form>
            </div>
    `
        $('body').append(deleteForm);
        const deleteModal = $('#deleteModal');
        deleteModal.modal({
            onCloseEnd: () => {
                deleteModal.remove();
            }
        });
        deleteModal.modal('open');

    });

    console.debug("init delete button end");
}
window.initDeleteButton = initDeleteButton;
initDeleteButton();