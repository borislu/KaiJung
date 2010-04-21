/**
 * Tries to convert a given XML data to a native JavaScript object by traversing the DOM tree.
 * If a string is given, it first tries to create an XMLDomElement from the given string.
 * 
 * @param {XMLDomElement|String} source The XML string or the XMLDomElement prefreably which containts the necessary data for the object.
 * @param {Boolean} [includeRoot] Whether the "required" main container node should be a part of the resultant object or not.
 * @return {Object} The native JavaScript object which is contructed from the given XML data or false if any error occured.
 */
Object.fromXML=function(source, includeRoot)
{
    if (typeof source=='string')
    {
        try
        {
                if (window.DOMParser)
                        source=(new DOMParser()).parseFromString(source, "application/xml");
                else if (window.ActiveXObject)
                {
                        var xmlObject=new ActiveXObject("Microsoft.XMLDOM");
                        xmlObject.async=false;
                        xmlObject.loadXML(source);
                        source=xmlObject;
                        xmlObject=undefined;
                }
                else
                        throw new Error("Cannot find an XML parser!");
        }
        catch(error)
        {
                return false;
        }
    }
    var result={};
    if (source.nodeType==9)
        source=source.firstChild;
    if (!includeRoot)
        source=source.firstChild;

    while (source) 
    {
        if (source.childNodes.length) 
        {
                if (source.tagName in result) 
                {
                        if (result[source.tagName].constructor != Array) 
                                result[source.tagName] = [result[source.tagName]];
                        result[source.tagName].push(Object.fromXML(source));
                }
                else 
                        result[source.tagName] = Object.fromXML(source);
        }
        else if (source.tagName)
                result[source.tagName] = source.nodeValue;
        else
                result = source.nodeValue;
        source = source.nextSibling;
    }

    return result;
};

