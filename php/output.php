<?php
require 'functions.php';
$response = array();
require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();

if (!isset($_GET['devices']) || !isset($_GET['events'])) {
    $response["message"] = "Required field(s) missing";
    echo json_encode($response);
    exit();
}

if ($_GET['devices'] != "0") {
    $sql = "SELECT id, deviceid From devices";
    $result = mysql_query($sql) or die(mysql_error());

  if (mysql_num_rows($result) > 0) {
      $response['devices'] = array();
 
      while ($row = mysql_fetch_array($result)) {
          $device = array();
          $device ["id"] = $row["id"];
          $device ["deviceid"] = $row["deviceid"];
          array_push($response['devices'], $device);
      }
    $response["message"]= "Devices successfully displayed.";
    echo json_encode($response);
  }else{
    $response["message"] = "No devices found";
    $response['devices'] = array();
    echo json_encode($response);
  }
}else if ($_GET['events'] != "0"){
    $sql = "SELECT id From devices";
    $result = mysql_query($sql) or die(mysql_error());

    if (mysql_num_rows($result) > 0) {
      
      while ($row = mysql_fetch_array($result)) {
         get_events($row["id"]);
      }

    }
  }else{
    $response["message"] = "No devices found";
    echo json_encode($response);
}
?>