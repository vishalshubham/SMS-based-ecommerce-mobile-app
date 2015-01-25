VERSION 5.00
Begin VB.Form Form1 
   Caption         =   "Form1"
   ClientHeight    =   3030
   ClientLeft      =   120
   ClientTop       =   450
   ClientWidth     =   4560
   LinkTopic       =   "Form1"
   ScaleHeight     =   3030
   ScaleWidth      =   4560
   StartUpPosition =   3  'Windows Default
   Begin VB.TextBox Text5 
      Height          =   495
      Left            =   4920
      TabIndex        =   5
      Top             =   6720
      Width           =   5055
   End
   Begin VB.CommandButton Command1 
      Caption         =   "YES"
      Height          =   495
      Left            =   6360
      TabIndex        =   4
      Top             =   7320
      Width           =   2175
   End
   Begin VB.TextBox Text4 
      Height          =   495
      Left            =   4920
      TabIndex        =   3
      Top             =   6120
      Width           =   5055
   End
   Begin VB.TextBox Text3 
      Height          =   495
      Left            =   4920
      TabIndex        =   2
      Top             =   5520
      Width           =   5055
   End
   Begin VB.TextBox Text2 
      Height          =   495
      Left            =   4920
      TabIndex        =   1
      Top             =   4920
      Width           =   5055
   End
   Begin VB.TextBox Text1 
      Height          =   2055
      Left            =   4920
      TabIndex        =   0
      Top             =   2760
      Width           =   5055
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub Command1_Click()
Dim str As String
Dim cnt As Integer
Dim last As Integer
str = Text1.Text
cnt = Len(str)
last = cnt - 46
Text2.Text = Mid$(str, 8, 13)
Text3.Text = Mid$(str, 25, 8)
Text4.Text = Mid$(str, 34, 8)
Text5.Text = Mid$(str, 47, last)
End Sub

