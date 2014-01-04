<?php
// include db connect class
    require_once __DIR__ . '/db_connect.php';
// connecting to db
    $db = new DB_CONNECT();
mysql_query("DELETE FROM songs WHERE 1");
// DBCC CHECKIDENT (songs, RESEED, 0)
if ($handle = opendir('D:\music\songs\\')) {
while (false !== ($file = readdir($handle))) {
if ($file != "." && $file != "..") {
$page_name=substr($file, 0, strpos($file, "."));
//jchdfnkjcnkdjncdkndkndkkjdcnkjdsncsdkcndsknckdnckdsncdkcndknckd
$path = "$page_name.mp3";
$path1 = "D:\music\songs\\";
//C:\xampp\htdocs\android_connect\songs
//dbncjkdnckjdnskjnckndskncsdkncsdkncksdncksnckndcksdncksncksdnkncs
$path2 = $path1 .$path; 
    $bytes = filesize($path2); 
    $bits = $bytes * 8; 
    $krate = 128; 
    $brate = $krate * 1024; 
    $seconds = $bits / $brate; 
    $minutes = floor($seconds / 60); 
    $seconds -= $minutes * 60;
	$page_length = $minutes . '.' . round($seconds,0);
//ndckjscnksdncksdncksncksncksdncksdncksdncksdncksdncknsdkcndkscnkdsnck
// set error reporting level
if (version_compare(phpversion(), '5.3.0', '>=') == 1)
  error_reporting(E_ALL & ~E_NOTICE & ~E_DEPRECATED);
else
  error_reporting(E_ALL & ~E_NOTICE);
// gathering all mp3 files in 'mp3' folder into array
$aFiles = array();
$aFiles[] = $path2;
// new object of our ID3TagsReader class
$oReader = new ID3TagsReader();
// passing through located files ..

foreach ($aFiles as $sSingleFile) {
    $aTags = $oReader->getTagsInfo($sSingleFile); // obtaining ID3 tags info
echo "<p>";
echo "$page_name";
echo "$page_length";
echo $aTags['Author'] ;

echo "</p>";
    $name = $page_name;
    $length = $page_length;
    $description = $aTags['Author'];


//insert into database
$result = mysql_query("INSERT INTO songs(name, length, description) VALUES('$name', '$length', '$description')");

}
//ncjsdkncksncksncknskncksncksncknckdsncksdncksncksncksncksncksncksncksk   

}
}
}
closedir($handle);

class ID3TagsReader {

    // variables
    var $aTV23 = array( // array of possible sys tags (for last version of ID3)
        'TIT2',
        'TALB',
        'TPE1',
        'TPE2',
        'TRCK',
        'TYER',
        'TLEN',
        'USLT',
        'TPOS',
        'TCON',
        'TENC',
        'TCOP',
        'TPUB',
        'TOPE',
        'WXXX',
        'COMM',
        'TCOM'
    );
    var $aTV23t = array( // array of titles for sys tags
        'Title',
        'Album',
        'Author',
        'AlbumAuthor',
        'Track',
        'Year',
        'Length',
        'Lyric',
        'Desc',
        'Genre',
        'Encoded',
        'Copyright',
        'Publisher',
        'OriginalArtist',
        'URL',
        'Comments',
        'Composer'
    );
    var $aTV22 = array( // array of possible sys tags (for old version of ID3)
        'TT2',
        'TAL',
        'TP1',
        'TRK',
        'TYE',
        'TLE',
        'ULT'
    );
    var $aTV22t = array( // array of titles for sys tags
        'Title',
        'Album',
        'Author',
        'Track',
        'Year',
        'Length',
        'Lyric'
    );

    // constructor
    function ID3TagsReader() {}

    // functions
    function getTagsInfo($sFilepath) {
        // read source file
        $iFSize = filesize($sFilepath);
        $vFD = fopen($sFilepath,'r');
        $sSrc = fread($vFD,$iFSize);
        fclose($vFD);

        // obtain base info
        if (substr($sSrc,0,3) == 'ID3') {
            $aInfo['FileName'] = $sFilepath;
            $aInfo['Version'] = hexdec(bin2hex(substr($sSrc,3,1))).'.'.hexdec(bin2hex(substr($sSrc,4,1)));
        }

        // passing through possible tags of idv2 (v3 and v4)
        if ($aInfo['Version'] == '4.0' || $aInfo['Version'] == '3.0') {
            for ($i = 0; $i < count($this->aTV23); $i++) {
                if (strpos($sSrc, $this->aTV23[$i].chr(0)) != FALSE) {

                    $s = '';
                    $iPos = strpos($sSrc, $this->aTV23[$i].chr(0));
                    $iLen = hexdec(bin2hex(substr($sSrc,($iPos + 5),3)));

                    $data = substr($sSrc, $iPos, 9 + $iLen);
                    for ($a = 0; $a < strlen($data); $a++) {
                        $char = substr($data, $a, 1);
                        if ($char >= ' ' && $char <= '~')
                            $s .= $char;
                    }
                    if (substr($s, 0, 4) == $this->aTV23[$i]) {
                        $iSL = 4;
                        if ($this->aTV23[$i] == 'USLT') {
                            $iSL = 7;
                        } elseif ($this->aTV23[$i] == 'TALB') {
                            $iSL = 5;
                        } elseif ($this->aTV23[$i] == 'TENC') {
                            $iSL = 6;
                        }
                        $aInfo[$this->aTV23t[$i]] = substr($s, $iSL);
                    }
                }
            }
        }

        // passing through possible tags of idv2 (v2)
        if($aInfo['Version'] == '2.0') {
            for ($i = 0; $i < count($this->aTV22); $i++) {
                if (strpos($sSrc, $this->aTV22[$i].chr(0)) != FALSE) {

                    $s = '';
                    $iPos = strpos($sSrc, $this->aTV22[$i].chr(0));
                    $iLen = hexdec(bin2hex(substr($sSrc,($iPos + 3),3)));

                    $data = substr($sSrc, $iPos, 6 + $iLen);
                    for ($a = 0; $a < strlen($data); $a++) {
                        $char = substr($data, $a, 1);
                        if ($char >= ' ' && $char <= '~')
                            $s .= $char;
                    }

                    if (substr($s, 0, 3) == $this->aTV22[$i]) {
                        $iSL = 3;
                        if ($this->aTV22[$i] == 'ULT') {
                            $iSL = 6;
                        }
                        $aInfo[$this->aTV22t[$i]] = substr($s, $iSL);
                    }
                }
            }
        }
        return $aInfo;
    }
}
?>


