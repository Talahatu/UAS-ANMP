<?php
extract($_POST);
$users = json_decode(file_get_contents("users.json"), true);
$result = "";
foreach ($users as $key => $value) {
    if ($value["email"] == $email && $value["password"] == $password) $result = json_encode(["status" => "Failed", "message" => "User already exist"]);
}
if ($result == "") {
    array_push($users, ["name" => $name, "email" => $email, "password" => $password]);
    file_put_contents("users.json", json_encode($users));
    echo json_encode(["status" => "Success", "message" => "Register success!"]);
} else echo $result;
