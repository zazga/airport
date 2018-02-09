var tableHelper;
var tableElement;
var selectedId;
var edit = false;

$(document).ready(function () {
    getTypes(function(result) {
            result.forEach(function(type) {
                $('#type').append('<option value=' + type.id + '>' + type.name + '</option>');
            });
        });

    getCA(function(result) {
                result.forEach(function(currentAirport) {
                    $('#currentAirport').append('<option value=' + currentAirport.name + '>' + currentAirport.name + '</option>');
                });
            });

//    // Load DataTable with data format.
    tableElement = $('#airplanesTable');
    tableHelper =  new DataTableHelper(tableElement, {
        bLengthChange: false,
        rowId: 'id',
        columns: [
           { "data": "type.name" },
           { "data": "type.passengers" },
           { "data": "fuel" },
           { "data": "type.mileage" },
           { "data": "currentAirport" }
        ]
    });

    updateTable();

    $('#create').on('click', function(event) {
        $('#airplaneModal .modal-title').html('Creating an airplane');
        $('#airplaneModal').modal('show');
    });
    $('#edit').on('click', function(event) {
        edit = true;
        var airplane = tableHelper.getSelectedRowData();
        setFormData(airplane);
        $('#airplaneModal .modal-title').html('Editing ' + airplane.name);
        $('#airplaneModal').modal('show');
    });
    $('#remove').on('click', function(event) {
        var airplane = tableHelper.getSelectedRowData();
        bootboxConfirm('Are you sure you want to delete this airplane?', function(result) {
        if (result == true){
            removeAirplane(airplane, function() {
                toastr.success('Removed "' + airplane.name + '" from Airplanes!');
                updateTable();
            },
            handleError);
        }
        else{
            $('#Modal').modal('toggle');
        }
        });
    });

    $('#airplaneForm').submit(function(event) {
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


    createairPlane(data, function(result) {
        toastr.success('Added to airplanes!');
        $('#airplaneForm').get(0).reset();
        updateTable();
        $('#airplaneModal').modal('hide');
    }, handleError)};

function handleEditFormSubmit() {
    var airplane = tableHelper.getSelectedRowData();
    var data = getFormData();
    _.extend(airplane, data);
    editAirplane(airplane, function(result) {
        toastr.success('Edited "' + data.name + '"');
        $('#airplaneForm').get(0).reset();
        updateTable();
        edit = false;
        $('#airplaneModal').modal('hide');
    }, handleError);
}

function handleError(error) {
    toastr.error(JSON.parse(error.responseText).message);
    console.log(error);
};

function createairPlane(data, successCallback, errorCallback) {
    console.log("Creating airplane..")
    var airplane = {
        currentAirport: data.currentAirport,
        fuel: data.fuel,
        type: {id:data.type}
    }

    ajaxJsonCall('POST', '/api/planes/create', airplane, successCallback, errorCallback )
    };

function editAirplane(airplane, successCallback, errorCallback) {
    ajaxJsonCall('POST', '/api/planes/edit', airplane, successCallback, errorCallback);
    console.log(airplane.id);
}

function getFormData() {
    return{
        currentAirport: $("#currentAirport").val(),
        type: $("#type").val(),
        fuel: $("#fuel").val()
    }
}

function removeAirplane(airplanes, successCallback, errorCallback) {
    ajaxJsonCall('DELETE', '/api/planes/delete/' + airplane.id, null, successCallback, errorCallback);
}

function setFormData(airplane) {
    $('#name').val(airplane.name);
    $('#currentAirport').val(airplane.currentAirport);
    $('#fuel').val(airplane.fuel);
    $('#runways').val(airplane.runways);
}

function updateTable() {
    console.log("Updating table..");

    $('button.controls').prop('disabled', selectedId === undefined);
    ajaxJsonCall('GET', '/api/planes/', null, function(result) {
      tableHelper.dataTable.clear();
      tableHelper.dataTable.rows.add(result);
      tableHelper.dataTable.columns.adjust().draw();
    }, null)
}

function getTypes(successCallback, errorCallback) {
    ajaxJsonCall('GET', '/api/types/', null, successCallback, errorCallback);
}

function getCA(successCallback, errorCallback) {
    ajaxJsonCall('GET', '/api/airports/', null, successCallback, errorCallback);
}