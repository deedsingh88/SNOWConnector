# Service Now Connector

This is a java connector that connects to snow table api and can do the transformation of data using xsl template. It also has a javascript code hook, where the result can be further refined/filter. It post the final payload back to any http endpoint.

## Installation

mvn install as a normal mvn project. The target directory will have a zip file with executables.

```bash
mkdir /tmp/snowconnector
cd /tmp/snowconnector
git clone https://url-for-this-project
mvn install

```

## Usage

```
java ${JAVA_OPTIONS} -jar SnowConnector-0.1.jar -c config/config.yaml

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
