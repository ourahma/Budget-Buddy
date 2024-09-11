   $(document).ready(function() {
    $('#modifierObjectifModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        var nom = button.data('nom');
        var montant = button.data('montant');
        var date = button.data('date');
        var initial = button.data('initial');
        var modal = $(this);
        modal.find('#id_objectif_mod').val(id);
        modal.find('#nom_objectif_mod').val(nom);
        modal.find('#montant_mod').val(montant);
        modal.find('#date_limite_mod').val(date);
        modal.find('#initial_amount_mod').val(initial);
    	modal.find('#current_deposited_mod').val(initial);
    });

    // Populate confirm delete modal with data
    $('#confirmDeleteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        var modal = $(this);
        modal.find('#id_objectif_supp').val(id);
    });

 // Populate deposit modal with data and add validation
    $('#depotModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var idObjectif = button.data('id');
        var montantObjectif = button.data('montant');
        var modal = $(this);
        modal.find('#id_objectif').val(idObjectif);
        modal.find('#objectif_montant').val(montantObjectif);

        // Add validation for montant_depot
        modal.find('#montant_depot').attr('max', montantObjectif);
    });

    // Populate withdrawal modal with data
    $('#retraitModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var idObjectif = button.data('id');
        var montantTotal = button.data('montant');
        var montantInitial = button.data('initial');
        var modal = $(this);
        modal.find('#id_objectif_retrait').val(idObjectif);
        modal.find('#montant_total').val(montantTotal);
        modal.find('#montant_initial').val(montantInitial);
    });
    
    
    $(document).ready(function() {
      $('#modifyDetteModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var id = button.data('id');
            var nom = button.data('nom');
            var description = button.data('description');
            var montant = button.data('montant');
            var date = button.data('date');
            var initialAmount = button.data('initialamount'); // Fixed typo here
            var modal = $(this);
            modal.find('#modify_id_dette').val(id);
            modal.find('#modify_nom_dette').val(nom);
            modal.find('#modify_description').val(description);
            modal.find('#modify_montant').val(montant);
            modal.find('#modify_date_limite').val(date);
            modal.find('#modify_initialAmount').val(initialAmount); // Added this line
        });

    $('#deleteDetteModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        
        var modal = $(this);
        modal.find('#delete_id_dette').val(id);
    });

    $('#addMontantModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        var montant = button.data('montant');
        var modal = $(this);
        modal.find('#add_montant_id_dette').val(id);
        modal.find('#add_montant').attr('max', montant);
    });
});
});