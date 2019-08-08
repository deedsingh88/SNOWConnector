# Service Now Connector

This is a java connector that connects to snow table api and can do the transformation of data using xsl template. It also has a javascript code hook, where the result can be further refined/filter. It post the final payload back to any http endpoint.

## Installation

mvn install as a normal mvn project. The target directory will have a zip file with executables.

```bash
cd /tmp/
git clone https://github.com/deedsingh88/SNOWConnector
cd SNOWConnector
mvn install

```

## Configuration File

You can maintain any number of configurations for the table api endpoint.
The file contains authentication information, table api target, web.append where you provide query parameters (**Please at the end do not delete 'sys_updated_on>='** - This is used by the code to retrieve data based on **timestamp**)
```
{
config-name: SnowCMDBToCustomApp, 
input.url.base: 'https://dev84127.service-now.com/api/now/table',
input.user: admin, 
input.password: pU4Fu6rDwcVM, 
input.url.web.target: /cmdb_ci,
input.url.web.append: sys_class_name=hardware^sys_updated_on>=,
input.xsl.mapping: 'config\Transformation.xsl',
input.from.time: !!timestamp '2019-06-01T07:40:58.953Z', output.url.base: 'https://localhost:9999/endpoint',
output.url.web.target: /api, 
filter.javascript.main: 'config\JavaScriptCodeHook.js'
}
```
If the execution is successfull in posting the result to the output endpoint, timestamp is changed to the current value.

## Usage

```
java -jar SnowConnector-0.1.jar -c config/config.yaml
```
If you are sitting behind a proxy server, you can set http proxy and other variables in JAVA_OPTIONS

```
java ${JAVA_OPTIONS} -jar SnowConnector-0.1.jar -c config/config.yaml
```

## Contributing
 
1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
 
## Credits
 
Lead Developer - Deed Singh (@deedsingh88)

 
## License
 
The MIT License (MIT)

Copyright (c) 2019 Deed Singh

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
