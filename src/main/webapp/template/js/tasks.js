function taskDelete(taskId) {
   $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/todo/tasks.do?id=' + taskId,
      statusCode: {
         404: function () {
            $('#err-text').val('Элемент с индексом ' + taskId + ' не найден.');
            $('.error-panel').removeClass('hidden');
         },
         403: function () {
            $('#err-text').html('Доступ запрещен. Авторизуйтесь чтобы изменять данные.');
            $('.error-panel').removeClass('hidden');
         },
         200: function () {
            location.reload();
         }
      }
   });
}

function taskGet(taskId) {
   $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/todo/tasks.do?id=' + taskId,
      dataType: 'json'
   }).done(function (data) {
      $('#task-id').val(data.id);
      $('#task-description').html(data.description);
      if (data.done > 0) {
         $('#task-done').attr('checked','1');
      } else {
         $('#task-done').removeAttr('checked');
      }
   }).fail(function(err) {
      $('#err-text').val('Провал получения элемента с индексом ' + taskId);
      $('#err-data').val(err);
      $('.error-panel').removeClass('hidden');
   });
}

function taskUpdate(taskId) {
   $.ajax({
      type: 'PUT',
      url: 'http://localhost:8080/todo/tasks.do',
      dataType: 'json',
      data: {
         id: taskId,
         description: $('#task-description').html(),
         done: $('#task-done').val() === 'on' ? 1 : 0,
      },
      statusCode: {
         406: function () {
            $('#err-text').html('Провал изменения элемента индексом ' + taskId);
            $('.error-panel').removeClass('hidden');
         },
         403: function () {
            $('#err-text').html('Доступ запрещен. Авторизуйтесь чтобы изменять данные.');
            $('.error-panel').removeClass('hidden');
         },
         200: function () {
            location.reload();
         }
      }
   });
}

function refreshRecords(surl) {
   let mContent = '';
   $('.table tbody').html(mContent);
   $.ajax({
      type: 'GET',
      url: surl,
      dataType: 'json'
   }).done(function (data) {
      if (data === undefined) {
         return false;
      }
      for (let task of data) {
         let status = task.done !== 0 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>';
         let actions = '<button class="btn btn-outline-secondary" onclick="taskGet('+ task.id + ')"><i class="fa fa-edit"></i></button>&nbsp;<button class="btn btn-outline-secondary" onclick="taskDelete('+ task.id +')"><i class="fa fa-times"></i></button>';
         let line = '<tr><td>' + status + '</td><td>' + task.description + '</td><td>' + task.author.login + '</td><td>' + task.created + '</td><td>' + actions + '</td></tr>';
         mContent += line;
      }
      $('#datatable tbody').html(mContent);
   }).fail(function(err) {
      console.log(err);
   });
}

$('#task-submit').click(function() {
   let taskId = $('#task-id').val();
   if (taskId > 0) {
      taskUpdate(taskId);
      return false;
   }
});

$(document).ready(function() {
   refreshRecords('http://localhost:8080/todo/tasks.do?scope=all');
});

$('#show-all').click(function() {
   $('#filter li button')
       .removeClass('btn-primary')
       .addClass('btn-secondary');
   $(this).removeClass('btn-secondary');
   $(this).addClass('btn-primary');
   refreshRecords('http://localhost:8080/todo/tasks.do?scope=all');
});

$('#show-opened').click(function() {
   $('#filter li button')
       .removeClass('btn-primary')
       .addClass('btn-secondary');
   $(this).removeClass('btn-secondary');
   $(this).addClass('btn-primary');
   refreshRecords('http://localhost:8080/todo/tasks.do?scope=open');
});