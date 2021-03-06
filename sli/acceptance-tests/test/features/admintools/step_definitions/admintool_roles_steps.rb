=begin

Copyright 2012-2013 inBloom, Inc. and its affiliates.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

=end


require "selenium-webdriver"
require 'mongo'

require_relative '../../utils/sli_utils.rb'
require_relative '../../utils/selenium_common.rb'

############################################################
# STEPS: BEFORE - for security event testing
############################################################

Before do
  @conn = Mongo::Connection.new(Property[:db_host], Property[:db_port])
end

When /^I navigate to the default Admin Page$/ do
  url = Property['admintools_server_url']
  @driver.get url
end

Then /^I am informed that authentication has failed$/ do
  @driver.find_element(:class, "alert-error").should_not be_nil
end
