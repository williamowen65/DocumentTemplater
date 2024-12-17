# Document Generation Tool

> This is a simple console application which can be used to populate custom templates (from txt files).

It is really simple, which is one of the reasons I like it. You cna write up literally any txt document and wrap placeholders in gt and lt (`<like so>`)  
But ideally you'd want the place holder text to be useful for you.


Here is an example which displays it's usefulness:


File name: email.txt
```txt

William Owen
Olalla, WA 98359
william.owen.career@gmail.com
123-456-7891

<Date>
<Recipient's Name>
<Recipient's Title>
<Company/Organization Name>

<Address> <City, State, ZIP Code>
Dear <Recipient's Name>,

I hope this letter finds you in great spirits. I am writing to <state the purpose of your letter, such as: “apply for the [Position Title] position,” “thank you for the opportunity to interview,” “inquire about [specific topic],” etc.>.

<Provide relevant context or background information, such as your qualifications, experiences, or the reason you are reaching out. Add specific examples or supporting details, depending on the nature of the letter.>

I would greatly appreciate it if you could <mention any requests, next steps, or actions needed, such as scheduling a meeting, reviewing attached materials, or responding to a question>. Please let me know if there’s any additional information I can provide to assist.

Thank you very much for your time and consideration. I look forward to hearing from you and hope we can connect soon.

Sincerely,
William Owen

```

> Open a terminal and run `java .\FillTemplate.java` to begin filling the template

```

```