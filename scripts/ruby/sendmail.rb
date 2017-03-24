# http://jerodsanto.net/2009/02/a-simple-ruby-method-to-send-emai/

require 'net/smtp'

def send_email(to,opts={})
  opts[:server]      ||= 'smtp.office365.com'
  opts[:from]        ||= 'nitendra.thakur@rakuten.com'
  opts[:from_alias]  ||= 'Thakur Nitendra'
  opts[:subject]     ||= "SMTP Test Subject"
  opts[:body]        ||= "SMTP Test Body"

  msg = <<END_OF_MESSAGE
From: #{opts[:from_alias]} <#{opts[:from]}>
To: <#{to}>
Subject: #{opts[:subject]}

#{opts[:body]}
END_OF_MESSAGE

  Net::SMTP.start(opts[:server]) do |smtp|
    smtp.send_message msg, opts[:from], to
  end

end

send_email "nitendra.thakur@rakuten.com", :body => "This was easy to send"
