var tableHelper;
var tableElement;
var selectedId;
var edit = false;

$(document).ready(function () {
    console.log("Document ready")

//    // Load DataTable with data format.
    tableElement = $('#airportsTable');
    tableHelper =  new DataTableHelper(tableElement, {
        bLengthChange: false,
        rowId: 'id',
        columns: [
           { "data": "name" },
           { "data": "longitude" },
           { "data": "latitude" }
        ]
    });

    updateTable();

    $('#create').on('click', function(event) {
        $('#airportModal .modal-title').html('Creating an airport');
        $('#airportModal').modal('show');
    });
    $('#edit').on('click', function(event) {
        edit = true;
        var airport = tableHelper.getSelectedRowData();
        setFormData(airport);
        $('#airportModal .modal-title').html('Editing ' + airport.name);
        $('#airportModal').modal('show');
    });
    $('#remove').on('click', function(event) {
        var airport = tableHelper.getSelectedRowData();
        bootboxConfirm('Are you sure you want to delete this room?', function(result) {
        if (result == true){
                        removeAirport(airport, function() {
                            toastr.success('Removed "' + airport.name + '" from Airports!');
                            updateTable();
                        },
                        handleError);
                    }
                    else{
                        $('#Modal').modal('toggle');
                    }

//
//
//
//            removeAirport(room, function() {
//                toastr.success('Removed "' + room.name + '" from Rooms!');
//                updateTable();
//            }, handleError);
        });
    });

    $('#airportForm').submit(function(event) {
        event.preventDefault();
        if (edit) {
            handleEditFormSubmit();
        } else {
            handleCreateFormSubmit();
        }
    });
})

function handleCreateFormSubmit() {
    var data = getFormData();

    createairport(data, function(result) {
        toastr.success('Added "' + data.name +' " to airports!');
        $('#airportForm').get(0).reset();
        updateTable();
        $('#airportModal').modal('hide');
    }, handleError)};
//}
//
function handleEditFormSubmit() {
    var airport = tableHelper.getSelectedRowData();
    var data = getFormData();
    _.extend(airport, data);
    editAirport(airport, function(result) {
        toastr.success('Edited "' + data.name + '"');
        $('#airportForm').get(0).reset();
        updateTable();
        edit = false;
        $('#airportModal').modal('hide');
    }, handleError);
}

function handleError(error) {
    toastr.error(JSON.parse(error.responseText).message);
    console.log(error);
};
//
function createairport(data, successCallback, errorCallback) {
    console.log("Creating airport..")

    var airport = {
        name: data.name,
        longitude: data.longitude,
        latitude: data.latitude,
    };

    ajaxJsonCall('POST', '/api/airports/create', airport, successCallback, errorCallback )
    };
//}
//
function editAirport(airport, successCallback, errorCallback) {
    ajaxJsonCall('POST', '/api/airports/edit', airport, successCallback, errorCallback);
    console.log(airport.id);
}
//
//function removeairport(airport, successCallback, errorCallback) {
//    console.log("Removing airport..")
//    ajaxJsonCall('DELETE', '/api/airports/delete/' + airport.id, null, successCallback, errorCallback);
//}
//
function getFormData() {
    return {
        name : $("#name").val(),
        longitude : $("#longitude").val(),
        latitude : $("#latitude").val()
//        telephoneNumber : $("#telephoneNumber").val(),
//        street : $("#street").val(),
//        houseNumber : $("#houseNumber").val(),
//        postalCode : $("#postalCode").val(),
//        city : $("#city").val(),
//        country : $("#country").val()
    };
}

function removeAirport(airport, successCallback, errorCallback) {
    ajaxJsonCall('DELETE', '/api/airports/delete/' + airport.id, null, successCallback, errorCallback);
}

function setFormData(airport) {
    $('#name').val(airport.name);
    $('#longitude').val(airport.longitude);
    $('#latitude').val(airport.latitude);
}
//
function updateTable() {
    console.log("Updating table..");

    $('button.controls').prop('disabled', selectedId === undefined);
    ajaxJsonCall('GET', '/api/airports/', null, function(airports) {
      tableHelper.dataTable.clear();
      tableHelper.dataTable.rows.add(airports);
      tableHelper.dataTable.columns.adjust().draw();}, null)
}