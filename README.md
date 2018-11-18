# home7bits
run junit tests in idea

how to run formatter to format file
1) mvn package
2) get Formatter*.jar (in target after package)
3) do in termital(linux) or in cmd(windows): java -jar Formatter*.jar CanonicalPathToFileToReadForFormat CanonicalPathToFileForFormattedOutput

(example java -jar Formatter-1.0.0-SNAPSHOT.jar ./maintext.txt ./out.txt)