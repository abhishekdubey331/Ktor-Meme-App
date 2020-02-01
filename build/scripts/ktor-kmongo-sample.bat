@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ktor-kmongo-sample startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and KTOR_KMONGO_SAMPLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ktor-kmongo-sample-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\ktor-server-netty-1.1.3.jar;%APP_HOME%\lib\ktor-jackson-1.1.3.jar;%APP_HOME%\lib\ktor-locations-1.1.3.jar;%APP_HOME%\lib\ktor-metrics-1.1.3.jar;%APP_HOME%\lib\koin-ktor-2.0.0-beta-1.jar;%APP_HOME%\lib\ktor-server-host-common-1.1.3.jar;%APP_HOME%\lib\ktor-auth-1.1.3.jar;%APP_HOME%\lib\ktor-server-core-1.1.3.jar;%APP_HOME%\lib\ktor-http-cio-1.1.3.jar;%APP_HOME%\lib\ktor-client-core-jvm-1.1.3.jar;%APP_HOME%\lib\ktor-http-jvm-1.1.3.jar;%APP_HOME%\lib\ktor-network-1.1.3.jar;%APP_HOME%\lib\ktor-utils-jvm-1.1.3.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.3.61.jar;%APP_HOME%\lib\logback-classic-1.2.1.jar;%APP_HOME%\lib\kmongo-coroutine-3.12.0.jar;%APP_HOME%\lib\koin-core-ext-2.0.0-beta-1.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.3.61.jar;%APP_HOME%\lib\kotlinx-coroutines-io-jvm-0.1.6.jar;%APP_HOME%\lib\kotlinx-io-jvm-0.1.6.jar;%APP_HOME%\lib\atomicfu-0.12.2.jar;%APP_HOME%\lib\kmongo-coroutine-core-3.12.0.jar;%APP_HOME%\lib\kmongo-jackson-mapping-3.12.0.jar;%APP_HOME%\lib\koin-core-2.0.0-beta-1.jar;%APP_HOME%\lib\kotlinx-coroutines-jdk8-1.1.1.jar;%APP_HOME%\lib\kotlinx-coroutines-reactive-1.3.3.jar;%APP_HOME%\lib\kotlinx-coroutines-core-1.3.3.jar;%APP_HOME%\lib\kmongo-async-shared-3.12.0.jar;%APP_HOME%\lib\kmongo-property-3.12.0.jar;%APP_HOME%\lib\kmongo-shared-3.12.0.jar;%APP_HOME%\lib\kmongo-id-jackson-3.12.0.jar;%APP_HOME%\lib\jackson-module-loader-0.1.0.jar;%APP_HOME%\lib\jackson-module-kotlin-2.10.2.jar;%APP_HOME%\lib\kmongo-id-3.12.0.jar;%APP_HOME%\lib\kreflect-1.0.0.jar;%APP_HOME%\lib\kotlin-reflect-1.3.61.jar;%APP_HOME%\lib\kmongo-data-3.12.0.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.61.jar;%APP_HOME%\lib\metrics-jvm-3.2.4.jar;%APP_HOME%\lib\metrics-core-3.2.4.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\config-1.3.1.jar;%APP_HOME%\lib\netty-codec-http2-4.1.24.Final.jar;%APP_HOME%\lib\alpn-api-1.1.3.v20160715.jar;%APP_HOME%\lib\logback-core-1.2.1.jar;%APP_HOME%\lib\kotlinx-coroutines-io-0.1.6.jar;%APP_HOME%\lib\kotlinx-coroutines-core-common-1.1.1.jar;%APP_HOME%\lib\kotlinx-io-0.1.6.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.61.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\atomicfu-common-0.12.2.jar;%APP_HOME%\lib\netty-codec-http-4.1.24.Final.jar;%APP_HOME%\lib\netty-handler-4.1.24.Final.jar;%APP_HOME%\lib\jackson-databind-2.10.2.jar;%APP_HOME%\lib\jackson-annotations-2.10.2.jar;%APP_HOME%\lib\json-simple-1.1.1.jar;%APP_HOME%\lib\bson4jackson-2.9.2.jar;%APP_HOME%\lib\netty-codec-4.1.24.Final.jar;%APP_HOME%\lib\netty-transport-4.1.24.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.24.Final.jar;%APP_HOME%\lib\junit-4.10.jar;%APP_HOME%\lib\mongodb-driver-reactivestreams-1.13.0.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\jackson-core-2.10.2.jar;%APP_HOME%\lib\netty-resolver-4.1.24.Final.jar;%APP_HOME%\lib\netty-common-4.1.24.Final.jar;%APP_HOME%\lib\hamcrest-core-1.1.jar;%APP_HOME%\lib\mongodb-driver-async-3.12.0.jar;%APP_HOME%\lib\mongodb-driver-core-3.12.0.jar;%APP_HOME%\lib\bson-3.12.0.jar

@rem Execute ktor-kmongo-sample
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %KTOR_KMONGO_SAMPLE_OPTS%  -classpath "%CLASSPATH%" com.cxyzy.ktor.demo.ApplicationKt %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable KTOR_KMONGO_SAMPLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%KTOR_KMONGO_SAMPLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
