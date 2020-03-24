import com.github.difflib.DiffUtils
import com.github.difflib.patch.Patch

class FileCompareClient {

    def original = new File("file1.csv").readLines()
    def revised  = new File("file2.csv").readLines()

    def patch = DiffUtils.diff(original, revised)


//    for(Object delta : patch.getDeltas()){
//        println delta
//    }
//    patch.getDeltas().each {
//        println it
//    }
}
