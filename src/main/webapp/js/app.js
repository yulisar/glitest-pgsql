var datarow
var tablemain

let rootpath = "/glitest-pgsql/api/user/"

let urlget = rootpath+"get-user"
let urlsave = rootpath+"save-user"
let urldelete = rootpath+"delete-user"        

$(document).ready(function () {
    clear_inputs();
    jQuery(document).ready(function ($) {
        if (window.jQuery().datetimepicker) {
            $('.datetimepicker').datetimepicker({
                // Formats
                // follow MomentJS docs: https://momentjs.com/docs/#/displaying/format/
                format: 'DD-MMM-YYYY',

                // Your Icons
                // as Bootstrap 4 is not using Glyphicons anymore
                icons: {
                    time: 'fa fa-clock-o',
                    date: 'fa fa-calendar',
                    up: 'fa fa-chevron-up',
                    down: 'fa fa-chevron-down',
                    previous: 'fa fa-chevron-left',
                    next: 'fa fa-chevron-right',
                    today: 'fa fa-check',
                    clear: 'fa fa-trash',
                    close: 'fa fa-times'
                }
            });
        }
    });

    $('[data-toggle="tooltip"]').tooltip()
    showData();
});



function showData() {
    $('#tableArea').show();
    $('#tableUser').dataTable().fnDestroy();

    tablemain = $('#tableUser').DataTable({
        "serverSide": true,
        "processing": true,
        "pageLength": 3,
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        ajax: {
            "url": urlget,
            "type": "GET",
            "dataType": "JSON",
            "contentType": "application/json",
            "data": function (data) {
                planify(data);
            }
        },
        columns: [
//            {data: 'iduser'},
            {data: 'nama'},
            {data: 'jenisKelamin', orderable: false},
            {data: 'tanggalLahir', orderable: false},
            {data: 'email', orderable: false},
            {data: 'alamat', orderable: false},
            {data: 'namaRole', orderable: false},
            {data: null, orderable: false}
        ],
        "columnDefs": [
            {
                "targets": -1,
                "data": null,
                "render": function (data, type, row, meta) {
                    return '<span id="add' + data.iduser + '" onclick="" class="span-onclick text text-success" data-toggle="tooltip" data-placement="top" title="Add row">Add</span> / <span id="edit' + data.iduser + '" onclick="" class="span-onclick text text-warning" data-toggle="tooltip" data-placement="top" title="Edit row">Edit</span> / <span id="delete' + data.iduser + '" onclick="" class="span-onclick text text-danger" data-toggle="tooltip" data-placement="top" title="Delete row">Delete</span> '
                }
            },
            {"width": "1%", "targets": 0},
            {"width": "8%", "targets": 1},
            {"width": "12%", "targets": 2},
            {"width": "8%", "targets": 3},
            {"width": "8%", "targets": 4},
            {"width": "8%", "targets": 5},
            {"width": "10%", "targets": 6}
        ]
    });

    $('#tableUser tbody').on('click', 'span', function () {
        let datarow = tablemain.row($(this).parents('tr')).data();
        console.log(datarow)

        if (this.id.includes('add')) {
            console.log("add action")
            clear_inputs();
            $('#buttonSave').prop('disabled', false);
            $('#update_mode_label').text("ADD USER")
                    .removeClass("label-warning")
                    .removeClass("label-info").removeClass("label-danger")
                    .addClass("label-success");
            $('#idupdate').text("");
            fillinputs(datarow);
        }
        if (this.id.includes('edit')) {
            console.log("edit action");
            $('#buttonSave').prop('disabled', false);
            $('#update_mode_label').text("EDIT USER")
                    .removeClass("label-success").removeClass("label-danger")
                    .addClass("label-warning");
            $('#idupdate').text(datarow.iduser);
            fillinputs(datarow);
        }
        if (this.id.includes('delete')) {
            console.log("edit action");
            $('#buttonSave').prop('disabled', true);
            $('#update_mode_label').text("DELETED USER")
                    .removeClass("label-success").addClass("label-danger");
            $('#idupdate').text(datarow.iduser);
            fillinputs(datarow);
            delete_user(datarow.iduser);
        }
    });
}

function fillinputs(datarow) {
    $('#input-nama').val(datarow.nama);
    $('#input-jenisKelamin').val(datarow.jenisKelamin);
    $('#input-dob').val(datarow.tanggalLahir);
    $('#input-email').val(datarow.email);
    $('#input-alamat').val(datarow.alamat);
    $('#input-role').val(datarow.namaRole);
}

//fumction to clean the structure of requeste array params
function planify(data) {
    for (var i = 0; i < data.columns.length; i++) {
        column = data.columns[i];
        column.searchRegex = column.search.regex;
        column.searchValue = column.search.value;
        delete(column.search);
    }
}

function delete_user(iduser) {
    var vouser = new Object();
    vouser.iduser = iduser;
    $.ajax({
        type: "POST",
        url: urldelete,
        async: false,
        headers: {
            'Content-Type': 'application/json'
        },
        dataType: 'json',
        async: false,
        data: JSON.stringify(vouser),
        success: function (responseData, status, xhr) {
            //location.reload();
            showData();
        },
        error: function (request, status, error) {
            //console.log("err");
            //console.log(request.responseText + " -- " + status +
            //       " -- " + error);
        }
    })
}

function saveUser() {
    var vouser = new Object();
    vouser.nama = $('#input-nama').val();
    vouser.jenisKelamin = $('#input-jenisKelamin').val();
    vouser.tanggalLahir = $('#input-dob').val();
    vouser.email = $('#input-email').val();
    vouser.alamat = $('#input-alamat').val();
    vouser.namaRole = $('#input-role').val();
    vouser.iduser = $('#idupdate').text();

    if (vouser.iduser === "") {
        vouser.iduser = -1;
    }
    console.log(vouser);
    $.ajax({
        type: "POST",
        url: urlsave,
        headers: {
            'Content-Type': 'application/json'
        },
        dataType: 'json',
        async: false,
        data: JSON.stringify(vouser),
        success: function (responseData, status, xhr) {
            console.log(responseData.nama);
            showData();

            if (responseData.nama === null) {
                alert("Save Error");
            }
        },
        error: function (request, status, error) {
            console.log("err");
            console.log(request.responseText + " -- " + status +
                    " -- " + error);
        }
    })
}

function confirmSave() {
    let everError = false;
    let  nama = $('#input-nama').val();
    let email = $('#input-email').val();
    let namaRole = $('#input-role').val();

    let everEmpty = nama === '' || namaRole === '' || email === '';

    console.log("NAMA " + nama);
    console.log("NAMAROLE " + namaRole);
    console.log("EMAIL " + email);

    if (everEmpty) {
        console.log("empty err..");
        everError = true;
        $("#field-empty-alert").fadeIn();
    }

    if (!validateEmail(email)) {
        $("#other-error-alert").fadeIn();
        everError = true;
        console.log("email format err..");
    }

    if (nama.length >= 50) {
        $("#other-error-alert").fadeIn();
        everError = true;
        console.log("name length err..");
    }

    console.log("Ever Error: " + everError);
    if (everError) {
        return false;
    } else {
        $("#field-empty-alert").fadeOut();
        $("#other-error-alert").fadeOut();
        saveUser();
    }
}

function toggler(divId) {
    $("#" + divId).toggle();
}

function clear_inputs() {

    $('#idupdate').text("");
    $('#input-nama').val("");
    $('#idupdate').val("");
    $('#input-jenisKelamin').val("");
    $('#input-dob').val("");
    $('#input-email').val("");
    $('#input-alamat').val("");
    $('#input-role').val("");
}

function showNoDataMessage() {
    $('#noDataMessage').removeClass("d-none");// .addClass("d-block");
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function changePage() {
    toggler('createuserdiv');
    toggler('listuser');
}

function homepage() {
    window.location.replace('/glitest-mysql');
}


