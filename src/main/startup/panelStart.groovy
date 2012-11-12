SYS='thepanel'

long startUpBeginMillis = System.currentTimeMillis()

BASTELECKE_HOME = System.getenv('BASTELECKE_HOME')
if (!BASTELECKE_HOME){
    BASTELECKE_HOME = '.'
}
 
toScriptOut "BASTELECKE_HOME=${BASTELECKE_HOME}"

JAVA_ENDORSED_DIRS = System.getenv('JAVA_ENDORSED_DIRS')
if (!JAVA_ENDORSED_DIRS || JAVA_ENDORSED_DIRS.isEmpty()){
    JAVA_ENDORSED_DIRS = ''
    toScriptOut "Resetting JAVA_ENDORSED_DIRS"
}
toScriptOut "JAVA_ENDORSED_DIRS=${JAVA_ENDORSED_DIRS}"

JAVA_HOME = System.getenv('JAVA_HOME')
if (!JAVA_HOME || JAVA_HOME.isEmpty()){
    toScriptOut "Set JAVA_HOME first"
    return
}
toScriptOut "JAVA_HOME=${JAVA_HOME}"


// Define relative variables.
PS_HOOK="sys=${SYS}srv"
// classpath resources
ETC_DIR="." 
// classpath jar files
LIB_DIR="${BASTELECKE_HOME}"
// logging
TRACE_DIR="${BASTELECKE_HOME}/log"
MAX_MEMORY='256m'
JPDA_ARGS="-Xdebug -Xrunjdwp:transport=dt_socket,address=12149,server=y,suspend=n"
//JPDA_ARGS=''

def dir = new File(LIB_DIR)	

def loader = this.class.classLoader.rootLoader

def classPathEntries = []
dir.eachFileMatch(~/.*\.jar$/){
    classPathEntries << it.absolutePath
	
    // Add the server's jars to the script classloader,
    loader.addURL(it.toURL())
}
classPathEntries <<  new File(ETC_DIR).absolutePath

CLASSPATH = classPathEntries.join(File.pathSeparator)
JAVA_CMD="${JAVA_HOME}/bin/java"


Process serverProcess = startServer()

/**
 * redirect servers's sout to the script's sout:
 */
serverProcess.consumeProcessOutput(System.out, System.err)


long startUpEndMillis = System.currentTimeMillis()

toScriptOut "${SYS} server is running... after ${startUpEndMillis-startUpBeginMillis} msec."
serverProcess.waitFor()

/**
 * #####################
 * function defintions
 * #####################
 */

/**
 * function which starts the server (execs a java process)
 */
def startServer(){
    def javaOptsList = [
	"-Xmx${MAX_MEMORY}",
	JPDA_ARGS,
	"-D${PS_HOOK}",
	"-Djava.endorsed.dirs=${JAVA_ENDORSED_DIRS}",
        "-DTRACEFILE=${TRACE_DIR}/${SYS}.log"
    ]
    JAVA_OPTS= javaOptsList.join('\u0020')
    JAVA_ARGS=""
    MAINCLASS='src.TcSound'
    CMD="${JAVA_CMD} ${JAVA_OPTS} -cp ${CLASSPATH} ${MAINCLASS} ${JAVA_ARGS}"
    
    toScriptOut "starting ${SYS}..."
    toScriptOut "${CMD}"
    return CMD.execute()
}

/**
 * function for printing messages to sout
 */
def toScriptOut(String text){
    println "${SYS}-start-script--->".plus(text)
}
