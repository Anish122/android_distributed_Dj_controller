<?php

/*
 * Following code will list all the songs
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all songs from songs table
$result = mysql_query("SELECT *FROM songs") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // songs node
    $response["songs"] = array();
 while ($row = mysql_fetch_array($result)) {
        // temp user array
        $song = array();
        $song["pid"] = $row["pid"];
        $song["name"] = $row["name"];
        $song["length"] = $row["length"];
        $song["description"] = $row["description"];
        $song["created_at"] = $row["created_at"];
        $song["updated_at"] = $row["updated_at"];
// push single song into final response array
        array_push($response["songs"], $song);
		
    }
    // success
    $response["success"] = 1;
// echoing JSON response
  echo json_encode($response);
  
  
  
} else {
    // no songs found
    $response["success"] = 0;
    $response["message"] = "No songs found";

    // echo no users JSON
    echo json_encode($response);
}
?>
