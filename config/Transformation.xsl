<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<result>
			<xsl:for-each select="/response/result">
				<root>
					<call_id />
					<cb_Topic />
					<delta>true</delta>
					<nestedStates>true</nestedStates>
					<prod_id>SnowIncidentToHIRO</prod_id>
					<sdfType>INCIDENT</sdfType>
					<<mand>
						<summary>
							<xsl:value-of select="short_description" />
						</summary>
						<sourceStatus>
							<xsl:value-of select="state" />
						</sourceStatus>
						<sourceId>
							<xsl:value-of select="number" />
						</sourceId>
						<assignedGroup>
						    <xsl:value-of select="number" />
						</assignedGroup>
						<normalizedStatus>
							New
						</normalizedStatus>
						<urgency>
							<xsl:value-of select="urgency" />
						</urgency>
						<sourceTicketId>
							<xsl:value-of select="number" />
						</sourceTicketId>
						<impact>
							<xsl:value-of select="urgency" />
						</impact>
						<description>
							<xsl:value-of select="description" />
						</description>
						<priority>
							<xsl:value-of select="priority" />
						</priority>
						<assigned_to><xsl:value-of select="assigned_to" /></assigned_to>
					</mand>
					<free>
						<affectedCI>
							<xsl:value-of select="cmdb_ci/value" />
						</affectedCI>
					</free>
				</root>
			</xsl:for-each>
		</result>
	</xsl:template>
</xsl:stylesheet>
