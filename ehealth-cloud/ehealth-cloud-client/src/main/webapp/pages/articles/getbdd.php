<!DOCTYPE html>
<html>
<?php
$q = intval($_GET['q']);
$con = mysqli_connect('jdbc:mysql://localhost/ehealth','appserver','123456','ehealth');
if (!$con) {
    die('Could not connect: ' . mysqli_error($con));
}

mysqli_select_db($con,"ehealth");
$sql="SELECT valeur FROM .$q.";
return $result = mysqli_query($con,$sql);

//mysqli_close($con);
?>
</html>