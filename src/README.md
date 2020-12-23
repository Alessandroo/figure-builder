# Figure builder

Project has three api for managing figures in database.<br/>
Main controller for managing pictures is availabe  via "/pictures" and GET, POST, PUT, DELETE methods.<br/>
To get all picture name use GET method via "/pictures/name".<br/>
Test JSON for PUT methos:<br/>
{
"name": "Alex",
"group": {
"childFigures": [
{
"circle": {
"borderStyle": "Dotted"
}
},
{
"square": {
"symbol": "A"
}
},
{
"triangle": {
"color": "#FFAF12"
}
}
],
"direction": "Horizontal",
"childGroups": [
{
"childFigures": [
{
"circle": {
"borderStyle": "Dotted"
}
},
{
"square": {
"symbol": "A"
}
},
{
"triangle": {
"color": "#FFAA12"
}
}
],
"direction": "Horizontal"
},
{
"childFigures": [
{
"circle": {
"borderStyle": "Dotted"
}
},
{
"square": {
"symbol": "A"
}
},
{
"triangle": {
"color": "#FFAA12"
}
}
],
"direction": "Horizontal"
},
{
"childFigures": [
{
"circle": {
"borderStyle": "Dotted"
}
},
{
"square": {
"symbol": "A"
}
},
{
"triangle": {
"color": "#FFAA12"
}
}
],
"direction": "Horizontal"
}
]
}
}