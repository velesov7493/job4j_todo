function refreshRecords() {
    let mContent = '';
    $('#datatable tbody').html(mContent);
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/roles.ajax',
        dataType: 'json'
    }).done(function (data) {
        if (data === undefined) {
            return false;
        }
        for (let role of data) {
            let actions = '<button class="btn btn-outline-secondary" onclick="roleGetById('+ role.id + ')"><i class="fa fa-edit"></i></button>&nbsp;<button class="btn btn-outline-secondary" onclick="roleDelete('+ role.id +')"><i class="fa fa-times"></i></button>';
            let line = '<tr><td>' + role.name + '</td><td>' + actions + '</td></tr>';
            mContent += line;
        }
        $('#datatable tbody').html(mContent);
    }).fail(function(err) {
        console.log(err);
    });
}

function roleDelete(roleId) {
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/todo/roles.ajax?id=' + roleId,
        statusCode: {
            404: function () {
                $('#err-text').val('Элемент с индексом ' + roleId + ' не найден.');
                $('.error-panel').removeClass('hidden');
            },
            403: function () {
                $('#err-text').html('Доступ запрещен. Авторизуйтесь чтобы изменять данные.');
                $('.error-panel').removeClass('hidden');
            },
            200: function () {
                refreshRecords();
            }
        }
    });
}

function roleUpdate(roleId) {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/todo/roles.ajax',
        processData: false,
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify({
            id: roleId,
            name: $('#role-name').val()
        }),
        statusCode: {
            406: function () {
                $('#err-text').html('Провал изменения элемента индексом ' + roleId);
                $('.error-panel').removeClass('hidden');
            },
            403: function () {
                $('#err-text').html('Доступ запрещен. Авторизуйтесь чтобы изменять данные.');
                $('.error-panel').removeClass('hidden');
            },
            200: function () {
                refreshRecords();
            }
        }
    });
}

function roleGetById(roleId) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/roles.ajax?id=' + roleId,
        dataType: 'json'
    }).done(function (data) {
        $('#role-id').val(data.id);
        $('#role-name').val(data.name);
    }).fail(function(err) {
        $('#err-text').val('Провал получения элемента с индексом ' + roleId);
        $('#err-data').val(err);
        $('.error-panel').removeClass('hidden');
    });
}

$(document).ready(function () {
    refreshRecords();
});

$('#role-submit').click(function () {
   let roleId = Number($('#role-id').val());
   if (roleId !== 0) {
       roleUpdate(roleId);
       return false;
   }
});