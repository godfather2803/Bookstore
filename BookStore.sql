USE [master]
GO
/****** Object:  Database [BookStoreL001]    Script Date: 12/11/2020 3:38:02 PM ******/
CREATE DATABASE [BookStoreL001]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookStoreL001', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\BookStoreL001.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BookStoreL001_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\BookStoreL001_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [BookStoreL001] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookStoreL001].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookStoreL001] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookStoreL001] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookStoreL001] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookStoreL001] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookStoreL001] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookStoreL001] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookStoreL001] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookStoreL001] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookStoreL001] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookStoreL001] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookStoreL001] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookStoreL001] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookStoreL001] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookStoreL001] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookStoreL001] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BookStoreL001] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookStoreL001] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookStoreL001] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookStoreL001] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookStoreL001] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookStoreL001] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookStoreL001] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookStoreL001] SET RECOVERY FULL 
GO
ALTER DATABASE [BookStoreL001] SET  MULTI_USER 
GO
ALTER DATABASE [BookStoreL001] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookStoreL001] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookStoreL001] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookStoreL001] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BookStoreL001] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'BookStoreL001', N'ON'
GO
ALTER DATABASE [BookStoreL001] SET QUERY_STORE = OFF
GO
USE [BookStoreL001]
GO
/****** Object:  Table [dbo].[tblBooks]    Script Date: 12/11/2020 3:38:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBooks](
	[bookID] [nvarchar](10) NOT NULL,
	[bookTitle] [nvarchar](100) NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[status] [bit] NOT NULL,
	[importDate] [datetime] NOT NULL,
	[image] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[author] [nvarchar](50) NULL,
	[categoryID] [int] NULL,
 CONSTRAINT [PK_tblProducts] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCategories]    Script Date: 12/11/2020 3:38:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategories](
	[categoryID] [int] NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblCategories] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 12/11/2020 3:38:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[discountID] [nvarchar](50) NOT NULL,
	[percent] [int] NOT NULL,
	[date] [datetime] NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[isUsed] [bit] NOT NULL,
 CONSTRAINT [PK_tblDiscount] PRIMARY KEY CLUSTERED 
(
	[discountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 12/11/2020 3:38:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [int] NOT NULL,
	[bookID] [nvarchar](10) NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 12/11/2020 3:38:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [int] NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[totalPrice] [float] NOT NULL,
	[date] [datetime] NOT NULL,
	[discountID] [nvarchar](100) NULL,
 CONSTRAINT [PK_tblOrders] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 12/11/2020 3:38:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 12/11/2020 3:38:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NULL,
	[address] [nvarchar](100) NULL,
	[phone] [nvarchar](20) NULL,
	[status] [bit] NOT NULL,
	[password] [nvarchar](max) NOT NULL,
	[roleID] [int] NOT NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[tblBooks] ([bookID], [bookTitle], [price], [quantity], [status], [importDate], [image], [description], [author], [categoryID]) VALUES (N'B001', N'PC Specify', 119000, 10, 1, CAST(N'2020-12-10T00:00:00.000' AS DateTime), N'', N'About PC specify', N'BP', 5)
INSERT [dbo].[tblBooks] ([bookID], [bookTitle], [price], [quantity], [status], [importDate], [image], [description], [author], [categoryID]) VALUES (N'B002', N'PC Specify 2', 119000, 10, 0, CAST(N'2020-12-10T00:00:00.000' AS DateTime), N'', N'About PC specify', N'BP', 5)
INSERT [dbo].[tblBooks] ([bookID], [bookTitle], [price], [quantity], [status], [importDate], [image], [description], [author], [categoryID]) VALUES (N'B003', N'Java script', 155000, 10, 0, CAST(N'2020-12-10T00:00:00.000' AS DateTime), N'', N'About JS', N'BP', 1)
INSERT [dbo].[tblBooks] ([bookID], [bookTitle], [price], [quantity], [status], [importDate], [image], [description], [author], [categoryID]) VALUES (N'B004', N'Java script 2', 190000, 2, 1, CAST(N'2020-12-10T00:00:00.000' AS DateTime), N'', N'About JS', N'BP', 1)
INSERT [dbo].[tblBooks] ([bookID], [bookTitle], [price], [quantity], [status], [importDate], [image], [description], [author], [categoryID]) VALUES (N'B005', N'Gaming', 75000, 2, 1, CAST(N'2020-12-10T00:00:00.000' AS DateTime), N'', N'About gaming', N'PS', 2)
INSERT [dbo].[tblBooks] ([bookID], [bookTitle], [price], [quantity], [status], [importDate], [image], [description], [author], [categoryID]) VALUES (N'B006', N'Outer', 75000, 0, 1, CAST(N'2020-12-10T00:00:00.000' AS DateTime), N'', N'About gaming', N'PS', 2)
GO
INSERT [dbo].[tblCategories] ([categoryID], [categoryName]) VALUES (1, N'Science')
INSERT [dbo].[tblCategories] ([categoryID], [categoryName]) VALUES (2, N'Survival')
INSERT [dbo].[tblCategories] ([categoryID], [categoryName]) VALUES (3, N'Life style')
INSERT [dbo].[tblCategories] ([categoryID], [categoryName]) VALUES (4, N'Self help')
INSERT [dbo].[tblCategories] ([categoryID], [categoryName]) VALUES (5, N'Magazine')
INSERT [dbo].[tblCategories] ([categoryID], [categoryName]) VALUES (6, N'Comic')
GO
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (1, N'ADMIN')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (2, N'CUSTOMER')
GO
INSERT [dbo].[tblUsers] ([userID], [name], [address], [phone], [status], [password], [roleID]) VALUES (N'admin', N'Bao', N'491', N'0879246503', 1, N'1', 1)
INSERT [dbo].[tblUsers] ([userID], [name], [address], [phone], [status], [password], [roleID]) VALUES (N'cus1', N'Bao', N'491', N'0879246503', 1, N'1', 2)
GO
USE [master]
GO
ALTER DATABASE [BookStoreL001] SET  READ_WRITE 
GO
