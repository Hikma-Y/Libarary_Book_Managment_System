<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>About Us - Library Book Record</title>
    <style>
        body {
            margin: 0;
            font-family: "Poppins", sans-serif;
            background: linear-gradient(135deg, #1f3c88, #4a6fa5);
            color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            min-height: 100vh;
        }
        .about-container {
            background: rgba(255,255,255,0.1);
            backdrop-filter: blur(8px);
            padding: 60px 40px;
            border-radius: 20px;
            max-width: 800px;
            margin-top: 100px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.2);
        }
        h1 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            color: #ffdd57;
        }
        p {
            line-height: 1.8;
            font-size: 1.1rem;
            color: #e0e7ff;
        }
        .team {
            margin-top: 40px;
        }
        .team h2 {
            color: #ffdd57;
            margin-bottom: 10px;
        }
        .team-members {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }
        .member {
            background: rgba(255,255,255,0.15);
            border-radius: 10px;
            padding: 15px 25px;
            width: 200px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <div class="about-container">
        <h1>About Our Library System</h1>
        <p>
            The Library Book Record System is designed to make book management simple, 
            efficient, and accessible for both librarians and readers. Our mission is 
            to modernize library operations while keeping the joy of reading alive.
        </p>
        <p>
            This system allows easy tracking of books, user accounts, and borrowing 
            activities ? all through an intuitive web interface.
        </p>

        <div class="team">
            <h2>Our Team</h2>
            <div class="team-members">
                <div class="member">
                    <strong>Developer:</strong><br> Your Name
                </div>
                <div class="member">
                    <strong> Librarian Advisor:</strong><br> Jane Doe
                </div>
                <div class="member">
                    <strong>System Designer:</strong><br> John Smith
                </div>
            </div>
        </div>
    </div>
</body>
</html>
