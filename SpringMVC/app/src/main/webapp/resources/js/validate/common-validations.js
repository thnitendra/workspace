/**
 * This file contains all the complex regex rules
 */

var ipRuleSet = {
        required: false,
        maxlength : 30000,
        pattern: /^(((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\/([3][0-2]|[1-2][0-9]|[8-9]))?)(,\s*(((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(\/([3][0-2]|[1-2][0-9]|[8-9]))?)*$/
};
var versionRuleSet = {
    minlength : 8,
    pattern: /^(?:(?:(?:1[6-9]|[2-9]\d)?\d{2})(?:(?:(?:0[13578]|1[02])31)|(?:(?:0[1,3-9]|1[0-2])(?:29|30)))|(?:(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))0229)|(?:(?:1[6-9]|[2-9]\d)?\d{2})(?:(?:0?[1-9])|(?:1[0-2]))(?:0?[1-9]|1\d|2[0-8]))$/
};

var qpsRuleSet = {
    required: false,
    max: 9999,
    min: 0,
    pattern: /^[0-9]+$/
};