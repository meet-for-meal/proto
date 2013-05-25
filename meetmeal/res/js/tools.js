
var CONTEXT_PATH = '/layoutMVC';

var Essilab = Essilab || {};
Essilab.apply = function(target, config ) {
  for (var p in config) {
    target[p] = config[p];
  }
};
Essilab.toString = function(object) {
  var result = [];
  for (var p in object) {
    result.push([p,'=',object[p]].join(' '));
  }
  return result.join(' ');
};
Essilab.FormBuilder = function(config) {
  this.tags = {
    text : 'input type="text"',
    select : 'select',
    textarea : 'textarea',
    checkbox : 'input type="checkbox"',
    radio : 'input type="radio"'
  };

  Essilab.apply(this, config || {});

  this.form = null;

  this.initializeForm = function()  {
    var me =this;
    if (!me.form) {
      me.form = $('<form/>');
      me.table = $('<table/>');
      me.form.append(me.table);
    }
  };
  this.addInput = function(type, attributes, listeners){
    attributes = attributes || {};
    if (!this.form) {
      this.initializeForm();
    }
    var component = $( ["<",this.tags[type], ' ', Essilab.toString(attributes,' '),"/>"].join('') ),
        line = $('<tr/>'),
        labelWrapper = $('<td/>'),
        componentWrapper = $('<td/>');
    component.data("attributes", attributes);
    Essilab.apply(component, attributes);
    line.append(labelWrapper, componentWrapper);
    labelWrapper.html(attributes.label);
    componentWrapper.append(component);
    component.on(listeners ||{});

    this.table.append(line);
    return this;
  };

  this.addButton = function(config, listeners){
    var me = this;
    if (!me.buttonWrapper){
      me.buttonWrapper = $('<div/>');
      me.form.append(me.buttonWrapper);
    }
    var button = $('<button/>');
    button.html(config.label);
    Essilab.apply(button, config);
    me.buttonWrapper.append(button);
    button.on(listeners);
  };

  this.getForm = function() {
    if (!this.form) {
      this.initializeForm();
    }
    return this.form;
  };
};


Essilab.MainMenu = function() {
  this.load = function(callback) {
    $.ajax({
      url : CONTEXT_PATH + '/user/display.ajax',
      success : function(json){
        callback.call(this, eval(json));
      }
    });
  };
};
