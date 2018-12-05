<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <strong>List Albums, songs, rock groups</strong>
    </div>
    <div class="panel-body">
        <form class="form-inline">
            <button type="button" onclick="ShowAllAlbums()">Show all albums</button>
            <button type="button" onclick="ShowAllSongPlayers()">Show all songplayers</button>
            <button type="button" onclick="ShowAllPeople()">Show all people</button>
            <button type="button" onclick="ShowAllRockGroups()">Show all rock groups</button>
        </form>
        <form class="form-inline">
            <label>Find by Song:</label>
            <select id="songSelect"></select>
            <button type="button" onclick="FindAllInstrumentalist()">Find all instrumentalist</button>
            <button type="button" onclick="FindAllAlbums()">Find all albums</button>
        </form>
        <form class="form-inline">
            <label>Find by Human:</label>
            <select id="humanSelect"></select>
            <button type="button" onclick="FindAllRockGroups()">Find all rock groups</button>
            <button type="button" onclick="FindAllSongs()">Find all songs</button>
        </form>
    </div>
    <div class="panel-body" id="tableAlbums"></div>
</div>
<script>
    var service = 'http://localhost:8080/';

    var FindAllSongs = function () {
        var humanSelect = document.getElementById('humanSelect');

        $.ajax({
            type: 'GET',
            url: service + 'people/getsongbyhuman/' + humanSelect.value,
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>human</'+'th>';
                output+= '<th>song</'+'th>';
                output+= '</' +'tr>';
                for (i in arrData) {
                    songPlayerObj = arrData[i];
                    output += '<tr>';
                    output += '<th>' + humanSelect.value + '</' + 'th>';
                    output += '<th>' + songPlayerObj.song + '</' + 'th>';
                    output += '</' + 'tr>';
                }
                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });

    };

    var FindAllRockGroups = function () {
        var humanSelect = document.getElementById('humanSelect');
        var rockGroupObj = {};
        $.ajax({
            type: 'GET',
            url: service + 'people/getbyhuman/' + humanSelect.value,
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>human</'+'th>';
                output+= '<th>rock group</'+'th>';
                output+= '</' +'tr>';

                for (i in arrData) {
                    rockGroupObj = arrData[i];
                    output += '<tr>';
                    output += '<th>' + humanSelect.value + '</' + 'th>';
                    output += '<th>' + rockGroupObj.rockGroup + '</' + 'th>';
                    output += '</' + 'tr>';
                }

                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    var FindAllAlbums = function () {
        var songSelect = document.getElementById('songSelect');
        var songPlayerObj = {};
        var albumArr = [];
        $.ajax({
            type: 'GET',
            url: service + 'songplayers/getalbumbysong/' + songSelect.value,
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>song</'+'th>';
                output+= '<th>album</'+'th>';
                output+= '</' +'tr>';

                for (i in arrData) {
                    // songPlayerObj = arrData[i];
                    output += '<tr>';
                    output += '<th>' + songSelect.value + '</' + 'th>';
                    output += '<th>' + arrData[i] + '</' + 'th>';
                    // output += '<th>' + songPlayerObj.album + '</' + 'th>';
                    output += '</' + 'tr>';
                }

                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    var FindAllInstrumentalist = function () {
        var songSelect = document.getElementById('songSelect');
        var songPlayerObj = {};
        var songInstrumentalistArr = [];
        $.ajax({
            type: 'GET',
            url: service + 'songplayers/getpeoplebysong/' + songSelect.value,
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>song</'+'th>';
                output+= '<th>instrumentalist</'+'th>';
                output+= '</' +'tr>';

                for (i in arrData) {
                    songPlayerObj = arrData[i];
                    output += '<tr>';
                    output += '<th>' + songSelect.value + '</' + 'th>';
                    output += '<th>' + songPlayerObj.human + '</' + 'th>';
                    output += '</' + 'tr>';
                    /*songPlayerObj = arrData[i];
                    songInstrumentalistArr = songPlayerObj.songInstrumentalistList;
                    for (k in songInstrumentalistArr){
                        output += '<tr>';
                        output += '<th>' + songSelect.value + '</' + 'th>';
                        output += '<th>' + songInstrumentalistArr[k].human + '</' + 'th>';
                        output += '</' + 'tr>';
                    }*/
                }

                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    var ShowAllRockGroups = function () {
        console.log('ShowAllRockGroups');
        $.ajax({
            type: 'GET',
            url: service + 'rockgroups/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>id</'+'th>';
                output+= '<th>RockGroup</'+'th>';
                output+= '</' +'tr>';

                for (i in arrData) {
                    output += '<tr>';
                    output += '<th>' + arrData[i].id + '</' + 'th>';
                    output += '<th>' + arrData[i].rockGroup + '</' + 'th>';
                    output += '</' + 'tr>';
                }

                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    var ShowAllPeople = function () {
        console.log('ShowAllPeople');
        $.ajax({
            type: 'GET',
            url: service + 'people/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>id</'+'th>';
                output+= '<th>Human</'+'th>';
                output+= '</' +'tr>';

                for (i in arrData) {
                    output += '<tr>';
                    output += '<th>' + arrData[i].id + '</' + 'th>';
                    output += '<th>' + arrData[i].human + '</' + 'th>';
                    output += '</' + 'tr>';
                }

                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }

        });
    };

    var ShowAllSongPlayers = function () {
        console.log('ShowAllSongPlayers');
        $.ajax({
            type: 'GET',
            url: service + 'songplayers/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>id</'+'th>';
                output+= '<th>Song</'+'th>';
                output+= '<th>Composer</'+'th>';
                output+= '<th>Poet</'+'th>';
                output+= '<th>Album</'+'th>';
                output+= '</' +'tr>';

                for (i in arrData) {
                    output += '<tr>';
                    output += '<th>' + arrData[i].id + '</' + 'th>';
                    output += '<th>' + arrData[i].song + '</' + 'th>';
                    output += '<th>' + arrData[i].composer + '</' + 'th>';
                    output += '<th>' + arrData[i].poet + '</' + 'th>';
                    output += '<th>' + arrData[i].album + '</' + 'th>';
                    output += '</' + 'tr>';
                }

                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    var ShowAllAlbums = function () {
        var songPlayersListArr = [];
        var songPlayersListObj = {};
        console.log('ShowAllAlbums');
        $.ajax({
            type: 'GET',
            url: service + 'album/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                var output = '';
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table-row-cell" border="1">';
                output+= '<tr>';
                output+= '<th>id</'+'th>';
                output+= '<th>Album</'+'th>';
                output+= '<th>Song</'+'th>';
                output+= '</' +'tr>';

                for (i in arrData) {
                    songPlayersListArr = arrData[i].songPlayersList;
                    for (k in songPlayersListArr) {
                        output += '<tr>';
                        output += '<th>' + arrData[i].id + '</' + 'th>';
                        output += '<th>' + arrData[i].album + '</' + 'th>';
                        songPlayersListObj = songPlayersListArr[k];
                        output += '<th>' + songPlayersListObj.song  + '</' + 'th>';
                        output += '</' + 'tr>';
                    }
                }

                output+= '</' +'table>';
                $('#tableAlbums').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    var SetSelectOptions = function (){
        console.log('SetSelectOptions');
        $.ajax({
            type: 'GET',
            url: service + 'songplayers/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                var stringData = JSON.stringify(result);
                // console.log(stringData);
                var arrData = JSON.parse(stringData);
                for (i in arrData) {
                    var songPlayersObj = {};
                    songPlayersObj = arrData[i];
                    $("#songSelect").append($('<'+'option value'+'="'+songPlayersObj.song+'">'+songPlayersObj.song+'</option>'))
                }
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    var SetSelectByHuman = function (){
        console.log('SetSelectByHuman');
        $.ajax({
            type: 'GET',
            url: service + 'people/all',
            dataType: 'json',
            async: false,
            success: function (result) {
                var stringData = JSON.stringify(result);
                var arrData = JSON.parse(stringData);
                // console.log(arrData);
                var arrData = JSON.parse(stringData);
                for (i in arrData) {
                    var peopleObj = {};
                    peopleObj = arrData[i];
                    $("#humanSelect").append($('<'+'option value'+'="'+peopleObj.human+'">'+peopleObj.human+'</option>'))
                }
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#tableAlbums').html(JSON.stringify(jqXHR))
            }
        });
    };

    SetSelectOptions();
    SetSelectByHuman();
</script>

</body>



</html>