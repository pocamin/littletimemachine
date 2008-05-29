if not "%JAVA_HOME%" == "" goto OkJHome
jre1.5.0\bin\javaw -classpath lib\log4j-1.2.13.jar;lib\poi-3.0.2.jar;lib\derby-10.2.2.0.jar;lib\antlr-2.7.2.jar;lib\asm-1.5.3.jar;lib\asm-attrs-1.5.2.jar;lib\c3p0-0.9.1.2.jar;lib\cglib-nodep-2.1.jar;lib\commons-collections-3.2.jar;lib\commons-logging-1.1.jar;lib\dom4j-1.4.jar;lib\ejb3-persistence.jar;lib\hibernate-3.2.6.jar;lib\hibernate-annotations.jar;lib\hibernate-commons-annotations.jar;lib\hibernate-entitymanager.jar;lib\hsqldb.jar;lib\jcommon-1.0.12.jar;lib\jfreechart-1.0.9.jar;lib\jta-1.0.jar;lib\junit.jar;bin timeMachine.runner.Runner
pause


goto end

:OkJHome
"%JAVA_HOME%\bin\javaw" -classpath lib\log4j-1.2.13.jar;lib\poi-3.0.2.jar;lib\derby-10.2.2.0.jar;lib\antlr-2.7.2.jar;lib\asm-1.5.3.jar;lib\asm-attrs-1.5.2.jar;lib\c3p0-0.9.1.2.jar;lib\cglib-nodep-2.1.jar;lib\commons-collections-3.2.jar;lib\commons-logging-1.1.jar;lib\dom4j-1.4.jar;lib\ejb3-persistence.jar;lib\hibernate-3.2.6.jar;lib\hibernate-annotations.jar;lib\hibernate-commons-annotations.jar;lib\hibernate-entitymanager.jar;lib\hsqldb.jar;lib\jcommon-1.0.12.jar;lib\jfreechart-1.0.9.jar;lib\jta-1.0.jar;lib\junit.jar;bin timeMachine.runner.Runner
pause
