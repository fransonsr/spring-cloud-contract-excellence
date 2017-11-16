# spring-cloud-contract-excellence

A hubristic name to be sure. 

## Purpose

This demo project is to demonstrate the use of Spring Cloud Contract and the 
generation and use of stub artifacts that clients can use to validate an API's
endpoint contracts. It was also used to investigate the use of Spring Shell 
to generate a CLI that talks to an API. 

## Structure

The demo consists of three applications: 1) a REST API; 2) a web UI that 
obtains data from the API; and 3) a similar CLI application that also gets 
its data from the API. The domain is a catalog of videos, VideoCat. 

### REST API

**Directory:** api
**URL:** http://localhost:8080/videos
**Execution:** `java -jar target/api-0.0.1-SNAPSHOT.jar`

The above URL returns a JSON representation of a list of videos. Currently, 
the API has an important security flaw: it exposes the synthetic database 
identifier for each video. It needs to remove this id from the representation
returned from its endpoint. This, of course, represents a non-backwards 
compatible change that would break clients (the CLI in this case). 
This can demonstrate how the use of Spring Cloud Contract can enforce the 
client-server contract and help identify the problems that can occur managing 
such contracts. 

### Web Client

**Directory:** web
**URL:** http://localhost:8081/
**Execution:** `java -jar target/web-0.0.1-SNAPSHOT.jar`

The above URL is a web page listing the videos obtained from the API. 

NOTE: the Spring Cloud Contract stub code uses WireMock during the test phase. 
As it does so, it registers the mock service on port 8080. Hence, the API 
cannot be running during the build, as there would be a port conflict.

### CLI

**Directory:** cli
**Execution:** `java -jar cli/target/cli-0.0.1-SNAPSHOT.jar`

Once in the shell, execute `help` to display the help screen. Note the `list` 
command. Issue that command from the shell and the CLI app will contact the 
API and display the list of videos.

Note that it is the CLI app that relies on the security-critical database id.
If the API were changed (including the videos.groovy file) the updated 
stub file would cause the test in the CLI to fail, demonstrating the 
use of Spring Cloud Contract and identifying breaches of contract between 
the server and its clients.

## Other notes

You will note that in the WEB and CLI tests, the `@AutoConfigureStubRunnter`
is configured with `workOffline=true` parameter. This forces stub runner to the 
local Maven repoistory to look for the stubs. This is only for this demo. In a 
real application of Spring Cloud Contract the parameter would need to be set
to `workOffline=false` to pull the latest stub artifact from Nexus. 
