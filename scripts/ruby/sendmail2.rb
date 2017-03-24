require 'net/smtp'
require 'ntlm/smtp'

message = <<MESSAGE_END
From: Private Person <nitendra.thakur@rakuten.com>
To: A Test User <nitendra.thakur@rakuten.com>
Subject: SMTP e-mail test

This is a test e-mail message.
MESSAGE_END

Net::SMTP.start('smtp.office365.com', 587, 'mail.rakuten.com', 'nitendra.thakur', 'Nt@11949', :ntlm) do |smtp|
  smtp.send_message message, 'nitendra.thakur@rakuten.com', 
                             'nitendra.thakur@rakuten.com'

end
